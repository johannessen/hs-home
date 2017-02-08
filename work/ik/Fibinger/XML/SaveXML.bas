Attribute VB_Name = "SaveXML"
Sub SaveXML()

' Original Author: Iris Fibinger
' Last Modified: Arne Johannessen, 2009-07-02

' Karlsruhe University of Applied Sciences
' Faculty of Geomatics
' Lecture 'Integrale Kartographie'


' attributes of the spreadsheet
' (change these when using different data)
Const BEZIRKE_WORKSHEET As String = "Bezirke"
Const BEZIRKE_START_ROW As Long = 15
Const BEZIRKE_KEY_CELL As String = "A15"
Const BEZIRKE_SORT_RANGE As String = "A15:C198"
Const KANTONE_WORKSHEET As String = "Kantone"
Const KANTONE_START_ROW As Long = 2
Const KANTONE_KEY_COL As Long = 7
Const KANTONE_DATA_START_COL As Long = 8
Const DATA_COL_COUNT As Long = 2

' name of the XML file to be created
' WARNING! if the file exists, it will be overwritten
Const XML_FILENAME = "data.xml"

Dim row, row2, col, rowCountKantone, kanton, thisKanton, dataRow(1 To DATA_COL_COUNT), kantonRow
Dim kantonGridX, kantonGridY, dataYayVotes, dataValidVotes As Long
Dim kantonKey As Variant
Dim kantonName, kantonId, kantonPixelX, kantonPixelY, dataYayRatio As String


Worksheets(BEZIRKE_WORKSHEET).Range(BEZIRKE_SORT_RANGE) _
        .Sort key1:=Worksheets(BEZIRKE_WORKSHEET).Range(BEZIRKE_KEY_CELL), order1:=xlAscending

' sum up the Bezirk worksheet's entire data,
' writing the results into the Kantone worksheet
Let thisKanton = -2  ' no previous Kanton
For col = 1 To UBound(dataRow)
    Let dataRow(col) = 0
Next
Let row = BEZIRKE_START_ROW
Do
    
    ' detect Kanton of current row
    kantonKey = Worksheets(BEZIRKE_WORKSHEET).Range("A:A").Cells(row)
    If kantonKey = "" Or Not IsNumeric(kantonKey) Then
        Let kanton = -1
    Else
        Let kanton = CLng(kantonKey / 100)
    End If
    
    ' if the Kanton has changed beginning with the current row,
    ' write out resulting data sums of previous Kanton
    If kanton <> thisKanton Then
        Let kantonRow = -1
        Let row2 = KANTONE_START_ROW
        Do
            Let kantonKey = Worksheets(KANTONE_WORKSHEET).Cells(row2, KANTONE_KEY_COL)
            If thisKanton = CLng(kantonKey) Then
                kantonRow = row2
                Exit Do
            End If
            Let row2 = row2 + 1
        Loop Until kantonKey = "" Or Not IsNumeric(kantonKey)
        If kantonRow > 0 Then
            For col = 1 To UBound(dataRow)
                Worksheets(KANTONE_WORKSHEET).Cells(kantonRow, KANTONE_DATA_START_COL + col - 1) = dataRow(col)
                Let dataRow(col) = 0
            Next
        End If
        thisKanton = kanton
        'MsgBox CStr(thisKanton) + " erkannt"
    End If
    
    ' sum up the data column by column
    For col = 1 To UBound(dataRow)
        Let dataRow(col) = dataRow(col) + Worksheets(BEZIRKE_WORKSHEET).Cells(row, col + 1)
    Next
    
    Let row = row + 1
Loop While kanton > 0


' output the XML file header
Open ThisWorkbook.Path & "\" & XML_FILENAME For Output As #1
Print #1, "<?xml version='1.0'?>"
Print #1, "<data>"

' iterate through all Kantone rows until there is no more data
Let row = KANTONE_START_ROW
Do
    Let kantonKey = Worksheets(KANTONE_WORKSHEET).Cells(row, KANTONE_KEY_COL)
    If kantonKey = "" Or Not IsNumeric(kantonKey) Then
        Exit Do
    End If
    Let kanton = CLng(kantonKey)
    
    ' gather result data from Kantone worksheet
    ' (change this when using a different Kantone worksheet layout)
    ' [Da in SVG bei Gleitkommazahlen der Punkt als Trennzeichen _
      verwendet wird, werden ueber die Funktion Replace() die in _
      EXCEL ueblichen Kommas durch Punkte ersetzt]
    Let kantonName = Worksheets(KANTONE_WORKSHEET).Range("A:A").Cells(row)
    Let kantonId = Worksheets(KANTONE_WORKSHEET).Range("B:B").Cells(row)
    Let kantonPixelX = Replace(Worksheets(KANTONE_WORKSHEET).Range("C:C").Cells(row), ",", ".")
    Let kantonPixelY = Replace(Worksheets(KANTONE_WORKSHEET).Range("D:D").Cells(row), ",", ".")
    Let kantonGridX = Worksheets(KANTONE_WORKSHEET).Range("E:E").Cells(row)
    Let kantonGridY = Worksheets(KANTONE_WORKSHEET).Range("F:F").Cells(row)
    Let dataYayVotes = Worksheets(KANTONE_WORKSHEET).Range("H:H").Cells(row)
    Let dataValidVotes = Worksheets(KANTONE_WORKSHEET).Range("I:I").Cells(row)
    Let dataYayRatio = Replace(Worksheets(KANTONE_WORKSHEET).Range("J:J").Cells(row), ",", ".")
    
    ' output one line of data to the XML file
    Print #1, Chr(9) & "<dataset" _
            & " name='" & kantonName & "'" _
            & " id='" & kantonId & "'" _
            & " key='" & kanton & "'" _
            & " centerX='" & kantonPixelX & "'" _
            & " centerY='" & kantonPixelY & "'" _
            & " chX='" & kantonGridX & "'" _
            & " chY='" & kantonGridY & "'" _
            & " yayVotes='" & dataYayVotes & "'" _
            & " validVotes='" & dataValidVotes & "'" _
            & " yayRatio='" & dataYayRatio & "'" _
            & " />"
    
    Let row = row + 1
Loop

' output the XML file footer
Print #1, "</data>"
Print #1, "<!-- Quelle: Bundesamt fuer Statistik (Schweiz) -->"
Close #1

ActiveWorkbook.Save

End Sub


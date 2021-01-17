package icu.jogeen.markbook.data;

import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class DataCenter {
    public static String SELECT_TEXT;
    public static String FILE_NAME;
    public static List<NoteData> NOTE_LIST = new LinkedList<>();

    public static String[] HEAD = {"title", "Note", "File", "Code"};

    public static DefaultTableModel TABLE_MODEL = new DefaultTableModel(null, HEAD);


    public static void add(NoteData noteData) {
        DataCenter.NOTE_LIST.add(noteData);
        DataCenter.TABLE_MODEL.addRow(DataConvert.convert(noteData));
    }

    public static void reset() {
        NOTE_LIST.clear();
        TABLE_MODEL.setDataVector(null, HEAD);
    }

    public static void update(Integer index, NoteData noteData) {
        NoteData temp = NOTE_LIST.get(index);
        temp.setTitle(noteData.getTitle());
        temp.setContent(noteData.getContent());
        temp.setFileName(noteData.getFileName());
        temp.setFileType(noteData.getFileType());
        temp.setMark(noteData.getMark());
        TABLE_MODEL.removeRow(index);
        TABLE_MODEL.insertRow(index, DataConvert.convert(noteData));
    }

    public static void delete(int index) {
        NoteData data = NOTE_LIST.remove(index);
        TABLE_MODEL.removeRow(index);
    }

}

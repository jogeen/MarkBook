package icu.jogeen.markbook.processor;

import icu.jogeen.markbook.data.NoteData;

import java.util.List;

public interface SourceNoteData {
    public String getFileName();

    public String getTopic();

    public List<NoteData> getNoteList();


}

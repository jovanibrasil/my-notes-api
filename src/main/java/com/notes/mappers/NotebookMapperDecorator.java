package com.notes.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.notes.model.Notebook;
import com.notes.model.dto.NotebookDTO;

public abstract class NotebookMapperDecorator implements NotebookMapper {

    private NotebookMapper notebookMapper;

    @Autowired
    public void setNotebookMapper(NotebookMapper notebookMapper) {
        this.notebookMapper = notebookMapper;
    }

    @Override
    public Notebook notebookDtoToNotebook(NotebookDTO notebookDTO) {
        Notebook notebook = this.notebookMapper.notebookDtoToNotebook(notebookDTO);
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        notebook.setUserName(currentUserName);
        return notebook;
    }

}

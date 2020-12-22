package com.rolex.microlabs.behavioral.visitor;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PDFFile implements ResourceFile {

    private String filePath;

    public PDFFile(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
package com.hasanozgan.optimus.managed.bean;

import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.*;

@ManagedBean(name="fileMB")
@RequestScoped
public class FileManagedBean
{
    public StreamedContent getImageFile(String fileName)
    {
        ExternalContext extContext= FacesContext.getCurrentInstance().getExternalContext();
        File imageFileWithRealPath = new File(extContext.getRealPath("//files//"+fileName));

        try {
            FileInputStream file = new FileInputStream(imageFileWithRealPath);
            return new DefaultStreamedContent(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}



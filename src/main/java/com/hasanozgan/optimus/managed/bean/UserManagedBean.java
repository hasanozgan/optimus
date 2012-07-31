package com.hasanozgan.optimus.managed.bean;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.dao.DataAccessException;

import com.hasanozgan.optimus.model.mongodb.User;
import com.hasanozgan.optimus.service.mongodb.UserService;

/**
 *
 * User Managed Bean
 *
 * @author onlinetechvision.com
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */
@ManagedBean(name="userMB")
@ViewScoped
public class UserManagedBean implements Serializable {


    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";

    //Spring User Service is injected...
    @ManagedProperty(value="#{userMongoService}")
    UserService userService;

    List<User> userList;

    private String name;
    private String surname;
    private String phone;

    private UploadedFile file = null;
    private StreamedContent fileStream = null;

    private static final int BUFFER_SIZE = 6124;

    public UserManagedBean()
    {
        name = "";
        surname = "";
        phone = "";
        file = null;
        fileStream = null;
    }

    public void handleFileUpload(FileUploadEvent event) {

        this.file = event.getFile();

        try {
            if (getFile() != null) {
                ExternalContext extContext= FacesContext.getCurrentInstance().getExternalContext();
                File result = new File(extContext.getRealPath("//files//" + getFile().getFileName()));
                FileOutputStream fileOutputStream = new FileOutputStream(result);

                byte[] buffer = new byte[BUFFER_SIZE];

                int bulk;
                InputStream inputStream = getFile().getInputstream();
                while (true) {
                    bulk = inputStream.read(buffer);
                    if (bulk < 0) {
                        break;
                    }
                    fileOutputStream.write(buffer, 0, bulk);
                    fileOutputStream.flush();
                }

                fileOutputStream.close();
                inputStream.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }
    /**
     * Add User
     *
     */
    public void addUser() {

        FacesMessage msg = null;
        RequestContext context = RequestContext.getCurrentInstance();

        try {

            User user = new User();
            user.setFirstName(getName());
            user.setLastName(getSurname());
            user.setPhone(getPhone());
            user.setFile(getFileName());
            getUserService().addUser(user);

            context.addCallbackParam("success", true);

        } catch (DataAccessException e) {
            context.addCallbackParam("success", false);
        }

    }

    /**
     * Reset Fields
     *
     */
    protected void reset() {
        this.setName("");
        this.setSurname("");
    }

    /**
     * Get User List
     *
     * @return List - User List
     */
    public List<User> getUserList() {
        /*userList = new ArrayList<User>();
        userList.addAll(getUserService().getUsers());*/
        return userService.getUserList();
    }

    /**
     * Get User Service
     *
     * @return UserService - User Service
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Set User Service
     *
     * @param UserService - User Service
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Set User List
     *
     * @param List - User List
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    /**
     * Get User Name
     *
     * @return String - User Name
     */
    public String getName() {
        return name;
    }

    /**
     * Set User Name
     *
     * @param String - User Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get User Surname
     *
     * @return String - User Surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set User Surname
     *
     * @param String - User Surname
     */
    public void setSurname(String surname) {
        // request if this request sent with post didnt ajax has bug at commons.upload
        //this.surname = new String(surname.getBytes("iso-8859-1"), "UTF-8");

        this.surname = surname;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFileName() {
        if (file != null) {
            return file.getFileName();
        }

        return null;
    }

    public void setFileName(String filename) {

    }
}
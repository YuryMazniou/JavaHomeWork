package by.it.mazniou.mvc.controller;

import by.it.mazniou.mvc.model.Model;
import by.it.mazniou.mvc.view.EditUserView;
import by.it.mazniou.mvc.view.UsersView;

public class Controller {
    private Model model;
    private UsersView usersView;
    private EditUserView editUserView;

    public void setEditUserView(EditUserView editUserView) {
        this.editUserView = editUserView;
    }
    public void setUsersView(UsersView usersView) {
        this.usersView = usersView;
    }
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
    public void onShowAllUsers(){
        model.loadUsers();
        usersView.refresh(model.getModelData());
    }
    public void onShowAllDeletedUsers(){
        model.loadDeletedUsers();
        usersView.refresh(model.getModelData());
    }
    public void onOpenUserEditForm(long userId) {
        model.loadUserById(userId);
        editUserView.refresh(model.getModelData());
    }
    public void onUserDelete(long id){
        model.deleteUserById(id);
        usersView.refresh(model.getModelData());
    }
    public void onUserChange(String name, long id, int level){
        model.changeUserData(name,id,level);
        editUserView.refresh(model.getModelData());
        usersView.refresh(model.getModelData());
    }
}

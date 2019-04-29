package by.it.mazniou.mvc;

import by.it.mazniou.mvc.controller.Controller;
import by.it.mazniou.mvc.model.MainModel;
import by.it.mazniou.mvc.model.Model;
import by.it.mazniou.mvc.view.EditUserView;
import by.it.mazniou.mvc.view.UsersView;

public class Solution {
    public static void main(String[] args) {
        Model model = new MainModel();
        UsersView usersView = new UsersView();
        EditUserView editUserView=new EditUserView();
        Controller controller = new Controller();

        usersView.setController(controller);
        editUserView.setController(controller);
        controller.setModel(model);
        controller.setUsersView(usersView);
        controller.setEditUserView(editUserView);

        usersView.fireEventShowAllUsers();
        usersView.fireEventOpenUserEditForm(126L);
        editUserView.fireEventUserDeleted(124L);
        editUserView.fireEventUserChanged("A",123,1);
        usersView.fireEventShowDeletedUsers();
    }
}
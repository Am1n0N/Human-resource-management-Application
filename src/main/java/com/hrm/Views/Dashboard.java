package com.hrm.Views;

import com.hrm.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.control.TabPane.TabClosingPolicy.SELECTED_TAB;

public class Dashboard implements Initializable {
    @FXML
    TabPane mainPane;

    public static TabPane corePane;

    @FXML
    Label userLabel;

    @FXML
    TreeView navList;

    @FXML
    Label adminLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userLabel.setText(Login.account.getEmail());

        mainPane.setTabClosingPolicy(SELECTED_TAB);
        TreeItem<String> newLeaf = new TreeItem<>("Main-Menu");
        navList.setRoot(newLeaf);
        List<TreeItem> leafs = new ArrayList<>();

        if (Boolean.parseBoolean(Login.account.getIsAdmin())) {
            adminLabel.setVisible(true);
            leafs.add(new TreeItem<>("Accueil", buildIcon("/Accueil.png")));
            leafs.add(new TreeItem<>("Profile", buildIcon("/Employee.png")));

            leafs.add(new TreeItem<>("Employees", buildIcon("/RE.png")));
            leafs.add(new TreeItem<>("Add Employee", buildIcon("/Laboratoire.png")));
            leafs.add(new TreeItem<>("Contracts", buildIcon("/Imagerie.png")));

            leafs.add(new TreeItem<>("Add Entry", buildIcon("/Hospitalisation.png")));
            leafs.add(new TreeItem<>("Entries", buildIcon("/BO.png")));
            leafs.add(new TreeItem<>("Requests", buildIcon("/SP.png")));

            leafs.add(new TreeItem<>("Add PTO", buildIcon("/Hospitalisation.png")));
            leafs.add(new TreeItem<>("PTO Request", buildIcon("/BO.png")));
            leafs.add(new TreeItem<>("PTOs", buildIcon("/SP.png")));

            navList.getRoot().getChildren().addAll(leafs);
        } else {
            adminLabel.setVisible(false);

            leafs.add(new TreeItem<>("Accueil", buildIcon("/Accueil.png")));
            leafs.add(new TreeItem<>("Profile", buildIcon("/Patients.png")));

            leafs.add(new TreeItem<>("Request Entry", buildIcon("/Hospitalisation.png")));
            leafs.add(new TreeItem<>("Entries", buildIcon("/BO.png")));

            leafs.add(new TreeItem<>("Request PTO", buildIcon("/Hospitalisation.png")));
            leafs.add(new TreeItem<>(" My PTOs", buildIcon("/SP.png")));

            navList.getRoot().getChildren().addAll(leafs);
        }
        navList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {

            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> old_val, TreeItem<String> new_val) {
                TreeItem<String> selectedItem = new_val;

                switch (selectedItem.getValue()) {
                    case "Accueil":
                        addTab("Accueil", "Accueil.png", mainPane);
                        break;
                    case "Profile":
                        removeTab("Profile", mainPane);
                        ProfileTab.account = Login.account;
                        addTab("Profile", "Patients.png", mainPane);
                        break;
                    case "Employees":
                        addTab("Employees", "RE.png", mainPane);
                        break;
                    case "Add Employee":
                        addTab("Add Employee", "Laboratoire.png", mainPane);
                        break;
                    case "Contracts":
                        addTab("Contracts", "Imagerie.png", mainPane);
                        break;
                    case "Add Entry":
                        addTab("Add Entry", "Hospitalisation.png", mainPane);
                        break;
                    case "Entries":
                        addTab("Entries", "BO.png", mainPane);
                        break;
                    case "Requests":
                        addTab("Requests", "SP.png", mainPane);
                        break;
                    case "Add PTO":
                        addTab("Add PTO", "Hospitalisation.png", mainPane);
                        break;
                    case "PTO Request":
                        addTab("PTO Request", "BO.png", mainPane);
                        break;
                    case "PTOs":
                        addTab("PTOs", "SP.png", mainPane);
                        break;
                    case "Request Entry":
                        addTab("Request Entry", "Hospitalisation.png", mainPane);
                        break;
                    case "Request PTO":
                        addTab("Request PTO", "Hospitalisation.png", mainPane);
                        break;
                    case " My PTOs":
                        addTab(" My PTOs", "SP.png", mainPane);
                        break;

                }

            }
        });

        corePane = mainPane;
    }

    private static ImageView buildIcon(String icon) {
        String imgPatch;
        try {
            imgPatch = Dashboard.class.getResource(icon).toString();
        } catch (NullPointerException ex) {
            imgPatch = Dashboard.class.getResource("/handIcon.png").toString();
        }
        ;

        Image i = new Image(imgPatch);
        ImageView imageView = new ImageView();
        imageView.setFitHeight(16);
        imageView.setFitWidth(16);
        imageView.setImage(i);
        return imageView;
    }
    private static boolean checkTab(String id, TabPane pane) {
        Boolean exists = false;
        for (Tab tab : pane.getTabs()) {
            if (tab.getId() == null) {
                continue;
            } else if (tab.getId().equals(id)) {
                exists = true;
            }
        }
        return exists;
    }
    private static Tab findTab(String id, TabPane mainPane) {
        Tab foundTab = null;
        if (!checkTab(id, mainPane)) {
            return null;
        } else {
            for (Tab tab : mainPane.getTabs()) {
                if (tab.getId() == null) {
                    continue;
                } else if (tab.getId().equals(id)) {
                    foundTab = tab;
                    break;
                }
            }
        }
        return foundTab;
    }

    public static void removeTab(String id, TabPane mainPane) {
        for (Tab tab : mainPane.getTabs()) {
            if (tab.getId() == null) {
                continue;
            } else if (tab.getId().equals(id)) {
                mainPane.getTabs().remove(tab);
                break;
            }
        }
    }

    public static void addTab(String name, String icon, TabPane pane) {
        if (!checkTab(name, pane)) {
            Tab newTab = new Tab(name);
            newTab.setGraphic(buildIcon(icon));
            newTab.setId(name);
            switch (name){
                case "Accueil":
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/AcceuilTab.fxml"));
                    try {
                        newTab.setContent(fxmlLoader.load());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "Profile":
                    FXMLLoader fxmlLoader1 = new FXMLLoader(Main.class.getResource("/ProfileTab.fxml"));
                    try {
                        newTab.setContent(fxmlLoader1.load());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "Employees":
                    FXMLLoader fxmlLoader2 = new FXMLLoader(Main.class.getResource("/EmployeesTab.fxml"));
                    try {
                        newTab.setContent(fxmlLoader2.load());
                    } catch (Exception e) {
                        System.out.println(e.getLocalizedMessage());
                    }
                    break;
                case "Add Employee":
                    FXMLLoader fxmlLoader3 = new FXMLLoader(Main.class.getResource("/AddEmployeeTab.fxml"));
                    try {
                        newTab.setContent(fxmlLoader3.load());
                    } catch (Exception e) {
                        System.out.println(e.getStackTrace());
                    }
                    break;
                case "Contracts":
                    FXMLLoader fxmlLoader4 = new FXMLLoader(Main.class.getResource("/ContractsTab.fxml"));
                    try {
                        newTab.setContent(fxmlLoader4.load());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "Add Entry":
                case "Request Entry":
                    FXMLLoader fxmlLoader5 = new FXMLLoader(Main.class.getResource("/AddEntryTab.fxml"));
                    try {
                        newTab.setContent(fxmlLoader5.load());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "Entries":
                    FXMLLoader fxmlLoader6 = new FXMLLoader(Main.class.getResource("/EntriesTab.fxml"));
                    try {
                        newTab.setContent(fxmlLoader6.load());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "Entry":
                    FXMLLoader fxmlLoader10 = new FXMLLoader(Main.class.getResource("/EntryTab.fxml"));
                    try {
                        newTab.setContent(fxmlLoader10.load());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                case "Requests":
                    FXMLLoader fxmlLoader7 = new FXMLLoader(Main.class.getResource("/RequestsTab.fxml"));
                    try {
                        newTab.setContent(fxmlLoader7.load());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "Add PTO":
                case "Request PTO":
                    FXMLLoader fxmlLoader8 = new FXMLLoader(Main.class.getResource("/AddPTOTab.fxml"));
                    try {
                        newTab.setContent(fxmlLoader8.load());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "My PTOs":
                case "PTOs":
                    FXMLLoader fxmlLoader9 = new FXMLLoader(Main.class.getResource("/PTOsTab.fxml"));
                    try {
                        newTab.setContent(fxmlLoader9.load());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "PTO Requests":
                    FXMLLoader fxmlLoader11 = new FXMLLoader(Main.class.getResource("/PTORequestsTab.fxml"));
                    try {
                        newTab.setContent(fxmlLoader11.load());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

            }
            pane.getTabs().add(newTab);
            pane.getSelectionModel().select(newTab);
        } else {
            pane.getSelectionModel().select(findTab(name, pane));
        }
    }
}

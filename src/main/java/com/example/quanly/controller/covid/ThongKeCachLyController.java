package com.example.quanly.controller.covid;

import com.example.quanly.Database;
import com.example.quanly.HelloApplication;
import com.example.quanly.controller.nhan_khau.ThemNhanKhauController;
import com.example.quanly.models.CachLy;
import com.example.quanly.models.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ThongKeCachLyController implements Initializable {

    @FXML
    private TextField searchTF;
    @FXML
    private TableView<CachLy> tableView;
    @FXML
    private TableColumn<CachLy, String> maCachLyColumn;
    @FXML
    private TableColumn<CachLy, Integer> idNhanKhauColumn;
    @FXML
    private TableColumn<CachLy, String> thoiGianKhaiBaoColumn;
    @FXML
    private TableColumn<CachLy, String> diaDiemCachLyColumn;
    @FXML
    private TableColumn<CachLy, String> tuNgayColumn;
    @FXML
    private TableColumn<CachLy, String> denNgayColumn;
    @FXML
    private TableColumn<CachLy, String> mucDoCachLyColumn;
    private ObservableList<CachLy> cachLyObservableList = FXCollections.observableArrayList();
    private ArrayList<CachLy> cachLyList;
    @FXML
    private ComboBox<String> comboBox;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] items = {"Mã cách ly", "Địa điểm cách ly", "ID nhân khẩu", "Thời gian khai báo", "Mức độ cách ly"};
        comboBox.getItems().addAll(items);
        comboBox.setValue(items[0]);

        maCachLyColumn.setCellValueFactory(new PropertyValueFactory<>("maCachLy"));
        idNhanKhauColumn.setCellValueFactory(new PropertyValueFactory<>("idNhanKhau"));
        thoiGianKhaiBaoColumn.setCellValueFactory(new PropertyValueFactory<>("thoiGianKhaiBao"));
        diaDiemCachLyColumn.setCellValueFactory(new PropertyValueFactory<>("diaDiemCachLy"));
        tuNgayColumn.setCellValueFactory(new PropertyValueFactory<>("tuNgay"));
        denNgayColumn.setCellValueFactory(new PropertyValueFactory<>("denNgay"));
        mucDoCachLyColumn.setCellValueFactory(new PropertyValueFactory<>("mucDoCachLy"));
        cachLyList = Database.findCachLy("*","");
        cachLyObservableList.addAll(cachLyList);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setItems(cachLyObservableList);

        searchTF.textProperty().addListener((observable, oldValue, newValue) -> {
            ObservableList<CachLy> filterList = FXCollections.observableArrayList();
            switch (comboBox.getValue()){
                case "Mã cách ly":
                    for(CachLy cachLy : cachLyList){
                        if(cachLy.getMaCachLy().toLowerCase().contains(newValue.toLowerCase())){
                            filterList.add(cachLy);
                        }
                    }
                    tableView.getItems().clear();
                    tableView.setItems(filterList);
                    break;

                case "Địa điểm cách ly":
                    for(CachLy cachLy : cachLyList){
                        if(cachLy.getDiaDiemCachLy().toLowerCase().contains(newValue.toLowerCase())){
                            filterList.add(cachLy);
                        }
                    }
                    tableView.getItems().clear();
                    tableView.setItems(filterList);
                    break;

                case "ID nhân khẩu":
                    for(CachLy cachLy : cachLyList){
                        String idNhanKhau = cachLy.getIdNhanKhau() + "";
                        if(idNhanKhau.toLowerCase().contains(newValue.toLowerCase())){
                            filterList.add(cachLy);
                        }
                    }
                    tableView.getItems().clear();
                    tableView.setItems(filterList);
                    break;

                case "Thời gian khai báo":
                    for(CachLy cachLy : cachLyList){
                        if(cachLy.getThoiGianKhaiBao().toLowerCase().contains(newValue.toLowerCase())){
                            filterList.add(cachLy);
                        }
                    }
                    tableView.getItems().clear();
                    tableView.setItems(filterList);
                    break;

                case "Mức độ cách ly":
                    for(CachLy cachLy : cachLyList){
                        if(cachLy.getMucDoCachLy().toLowerCase().contains(newValue.toLowerCase())){
                            filterList.add(cachLy);
                        }
                    }
                    tableView.getItems().clear();
                    tableView.setItems(filterList);
                    break;

            }
        });

        tableView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                try {
                    // load the fxml file and create a new popup dialog
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("covid/ghi_nhan_thong_tin_cach_ly.fxml"));
                    DialogPane dialogPane = fxmlLoader.load();

                    Dialog<ButtonType> dialog = new Dialog<>();
                    dialog.setDialogPane(dialogPane);
                    dialog.setTitle("Thông tin cách ly");

                    CachLy cachLy = tableView.getSelectionModel().getSelectedItem();
                    GhiNhanThongTinCachLyController controller = fxmlLoader.getController();
                    controller.showThongTinCachLy(cachLy);
                    Optional<ButtonType> clickedButton = dialog.showAndWait();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @FXML
    private void onTimKiemBtnClick(){

    }
}

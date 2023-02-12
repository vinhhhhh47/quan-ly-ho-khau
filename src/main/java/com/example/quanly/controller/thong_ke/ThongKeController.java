package com.example.quanly.controller.thong_ke;

import com.example.quanly.Database;
import com.example.quanly.models.HoKhau;
import com.example.quanly.models.NhanKhau;
import com.example.quanly.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ThongKeController implements Initializable {
    @FXML
    private TextField fromTF, toTF;
    @FXML
    private Button searchBtn;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TableView<NhanKhau> tableView;
    @FXML
    private TableColumn<HoKhau, String> maNhanKhau;
    @FXML
    private TableColumn<HoKhau, String> hoTen;
    @FXML
    private TableColumn<HoKhau, String> gioiTinh;
    @FXML
    private TableColumn<HoKhau, String> namSinh;
    @FXML
    private TableColumn<HoKhau, String> diaChiHienNay;
    ObservableList<NhanKhau> listview = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] items = {"Nam", "Nữ"};
        comboBox.getItems().addAll(items);
        comboBox.setValue(items[0]);

        maNhanKhau.setCellValueFactory(new PropertyValueFactory<>("maNhanKhau"));
        hoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        gioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        namSinh.setCellValueFactory(new PropertyValueFactory<>("namSinh"));
        diaChiHienNay.setCellValueFactory(new PropertyValueFactory<>("diaChiHienNay"));
        ArrayList<NhanKhau> list = Database.findNhanKhau("*", "");
        listview.addAll(list);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setItems(listview);
    }
    @FXML
    void onTimKiemBtnClick(){
        if(fromTF.getText().equals("") || toTF.getText().equals("")) return ;
        int from = Integer.parseInt(fromTF.getText());
        int to = Integer.parseInt(toTF.getText());
        String gen = comboBox.getValue();
        ArrayList<NhanKhau> list = Database.findNhanKhau("*", "");
        ArrayList<NhanKhau> _list = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            NhanKhau nhanKhau = list.get(i);
            System.out.println(nhanKhau.getGioiTinh());
            if(nhanKhau.getGioiTinh().equals(gen)){
                int age = Utils.AgeCalc(nhanKhau.getNamSinh());
                System.out.println(age);
                if(from <= age && age <= to){
                    _list.add(nhanKhau);
                }
            }
        }

        listview.clear();
        listview.addAll(_list);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setItems(listview);
    }
}
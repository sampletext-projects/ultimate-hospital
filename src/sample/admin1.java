package sample;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class admin1 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<User> doctor;

    @FXML
    private TableColumn<User, String> id;

    @FXML
    private TableColumn<User, String> name;

    @FXML
    private TableColumn<User, String> login;

    @FXML
    private TableColumn<User, String> pass;

    @FXML
    private TableColumn<User, String> time;

    @FXML
    private TableColumn<User, String> data;

    @FXML
    private Button addbutton;

    @FXML
    private Button deletebutton;

    @FXML
    private Button upbutton;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_login;

    @FXML
    private TextField txt_pass;

    @FXML
    private TextField txt_data;

    @FXML
    private TextField txt_time;

    ObservableList<User> listM;
    ObservableList<User> dataList;
    int index = -1;
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void Add_users (){
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into doctor (name,login,pass,data,time)values(?,?,?,?,? )";
        try {

            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_name.getText());
            pst.setString(2, txt_login.getText());
            pst.setString(3, txt_pass.getText());
            pst.setString(4, txt_data.getText());
            pst.setString(5, txt_time.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Успех!");
            UpdateTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }


    //////// methode select users ///////
    @FXML
    void getSelected (MouseEvent event){
        index = doctor.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        txt_id.setText(id.getCellData(index).toString());
        txt_name.setText(name.getCellData(index).toString());
        txt_login.setText(login.getCellData(index).toString());
        txt_pass.setText(pass.getCellData(index).toString());
        txt_data.setText(data.getCellData(index).toString());
        txt_time.setText(time.getCellData(index).toString());

    }

    public void Edit (){
        try {
            conn = mysqlconnect.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_name.getText();
            String value3 = txt_login.getText();
            String value4 = txt_pass.getText();
            String value5 = txt_data.getText();
            String value6 = txt_time.getText();
            String sql = "update doctor set id= '"+value1+"',name= '"+value2+"',login= '"+value3+"',pass= '"+value4+"',data= '"+value5+"',time= '"+value6+"' where id='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void Delete(){
        conn = mysqlconnect.ConnectDb();
        String sql = "delete from doctor where id = ?";
        try {
            assert conn != null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }


    public void UpdateTable(){
        id.setCellValueFactory(new PropertyValueFactory<User,String>("id"));
        name.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        login.setCellValueFactory(new PropertyValueFactory<User,String>("login"));
        pass.setCellValueFactory(new PropertyValueFactory<User,String>("pass"));
        time.setCellValueFactory(new PropertyValueFactory<User,String>("time"));
        data.setCellValueFactory(new PropertyValueFactory<User,String>("data"));

        listM = mysqlconnect.getDatausers();
        doctor.setItems(listM);
    }

    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
    }
}

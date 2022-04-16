import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.*;

public class NFA_DFA_MFA implements ActionListener {
    JFrame fa_frame;
    JPanel North,Center,west,center,east,bottom_nfa,read_nfa_panel,
            bottom_dfa,read_dfa_panel,bottom_mfa,read_mfa_panel;
    JLabel exp_label,input_label,nfa,dfa,mfa,lab18,lab19,lab21,lab20,lab27,lab26,exam_label;
    JTextField jTextField;
    JButton verify,reset,read_nfa,create_nfa,save_nfa,
            read_dfa,create_dfa,save_dfa,read_mfa,create_mfa,save_mfa;
    JTable nfa_table,dfa_table,mfa_table;
    JScrollPane nfa_scroll,dfa_scroll,mfa_scroll;
    String Column[]={"起始状态","接受符号","到达状态"};
    DefaultTableModel model_nfa,model_dfa,model_mfa;
    FileDialog open_file,save_file;
    String nfa_str,dfa_str,mfa_str,nor_str;
    String []nfa_start,nfa_end,Sigma;
    ArrayList<FaList> nfa_list=new ArrayList<>(),dfa_list=new ArrayList<>(),mfa_list=new ArrayList<>();
    char epsilon='#';
    NFA_DFA_MFA(){
        Init();
        Layout();
        AddListener();
        Setting();
    }
    void Init(){
        fa_frame=new JFrame("NFA_DFA_MFA");
        North=new JPanel();Center=new JPanel();
        west=new JPanel();center=new JPanel();east=new JPanel();
        bottom_nfa=new JPanel();read_nfa_panel=new JPanel();
        bottom_dfa=new JPanel();read_dfa_panel=new JPanel();
        bottom_mfa=new JPanel();read_mfa_panel=new JPanel();
        exp_label=new JLabel("表达式---->");
        input_label=new JLabel("请输入一个正规式：");
        exam_label=new JLabel("例如: (a*|b)*  ");
        jTextField=new JTextField(30);
        //Button
        verify=new JButton("验证正规式");
        reset=new JButton("重置");
        read_nfa=new JButton("读入NFA文件");
        create_nfa=new JButton("生成NFA文件");
        save_nfa=new JButton("保存");
        read_dfa=new JButton("读入DFA文件");
        create_dfa=new JButton("生成DFA文件");
        save_dfa=new JButton("保存");
        read_mfa=new JButton("读入MFA文件");
        create_mfa=new JButton("生成MFA文件");
        save_mfa=new JButton("保存");
        nfa=new JLabel("正规式---->NFA");
        dfa=new JLabel("NFA---->DFA");
        mfa=new JLabel("DFA---->MFA");
        //bottom_label
        lab18=new JLabel("初始状态集：label18");
        lab19=new JLabel("终止状态集：label19");
        lab21=new JLabel("初始状态集：label21");
        lab20=new JLabel("终止状态集：label20");
        lab26=new JLabel("初始状态集：label26");
        lab27=new JLabel("终止状态集：label27");
        open_file=new FileDialog(fa_frame,"打开文件",FileDialog.LOAD);
        save_file=new FileDialog(fa_frame,"保存文件",FileDialog.SAVE);
        //table
        nfa_table=new JTable();
        dfa_table=new JTable();
        mfa_table=new JTable();
        nfa_scroll=new JScrollPane(nfa_table);
        dfa_scroll=new JScrollPane(dfa_table);
        mfa_scroll=new JScrollPane(mfa_table);
    }
    void Layout(){
        //上方
        fa_frame.setLayout(new FlowLayout());
        North.add(exp_label);
        North.add(input_label);
        North.add(jTextField);
        North.add(exam_label);
        North.add(verify);
        North.add(reset);
        //中左
        read_nfa_panel.add(read_nfa);
        read_nfa_panel.add(create_nfa);
        read_nfa_panel.add(save_nfa);
        bottom_nfa.setLayout(new BorderLayout());
        bottom_nfa.add(lab18,BorderLayout.NORTH);
        bottom_nfa.add(lab19,BorderLayout.CENTER);
        bottom_nfa.add(read_nfa_panel,BorderLayout.SOUTH);
        west.setLayout(new BorderLayout());
        west.add(nfa,BorderLayout.NORTH);
        west.add(nfa_scroll,BorderLayout.CENTER);
        west.add(bottom_nfa,BorderLayout.SOUTH);
        //中中
        read_dfa_panel.add(read_dfa);
        read_dfa_panel.add(create_dfa);
        read_dfa_panel.add(save_dfa);
        bottom_dfa.setLayout(new BorderLayout());
        bottom_dfa.add(lab21,BorderLayout.NORTH);
        bottom_dfa.add(lab20,BorderLayout.CENTER);
        bottom_dfa.add(read_dfa_panel,BorderLayout.SOUTH);
        center.setLayout(new BorderLayout());
        center.add(dfa,BorderLayout.NORTH);
        center.add(dfa_scroll,BorderLayout.CENTER);
        center.add(bottom_dfa,BorderLayout.SOUTH);
        //中右
        read_mfa_panel.add(read_mfa);
        read_mfa_panel.add(create_mfa);
        read_mfa_panel.add(save_mfa);
        bottom_mfa.setLayout(new BorderLayout());
        bottom_mfa.add(lab27,BorderLayout.NORTH);
        bottom_mfa.add(lab26,BorderLayout.CENTER);
        bottom_mfa.add(read_mfa_panel,BorderLayout.SOUTH);
        east.setLayout(new BorderLayout());
        east.add(mfa,BorderLayout.NORTH);
        east.add(mfa_scroll,BorderLayout.CENTER);
        east.add(bottom_mfa,BorderLayout.SOUTH);
        Center.setLayout(new BorderLayout());
        Center.add(west,BorderLayout.WEST);
        Center.add(center,BorderLayout.CENTER);
        Center.add(east,BorderLayout.EAST);
        //父组件
        fa_frame.add(North);
        fa_frame.add(Center);
    }
    void Setting(){
        model_nfa=(DefaultTableModel) nfa_table.getModel();
        model_dfa=(DefaultTableModel) dfa_table.getModel();
        model_mfa=(DefaultTableModel) mfa_table.getModel();
        model_nfa.addColumn(Column[0]);model_nfa.addColumn(Column[1]);model_nfa.addColumn(Column[2]);
        model_dfa.addColumn(Column[0]);model_dfa.addColumn(Column[1]);model_dfa.addColumn(Column[2]);
        model_mfa.addColumn(Column[0]);model_mfa.addColumn(Column[1]);model_mfa.addColumn(Column[2]);
        nfa_table.setPreferredScrollableViewportSize(new Dimension(200, 300));
        dfa_table.setPreferredScrollableViewportSize(new Dimension(200, 300));
        mfa_table.setPreferredScrollableViewportSize(new Dimension(200, 300));
        create_nfa.setEnabled(false);create_dfa.setEnabled(false);create_mfa.setEnabled(false);
        fa_frame.setBounds(100,100,900,500);
        jTextField.setPreferredSize(new Dimension(50,25));
        fa_frame.setVisible(true);
    }
    void AddListener(){
//        JButton verify,reset,read_nfa,create_nfa,save_nfa,
//                read_dfa,create_dfa,save_dfa,read_mfa,create_mfa,save_mfa;
        verify.addActionListener(this);reset.addActionListener(this);
        read_nfa.addActionListener(this);create_nfa.addActionListener(this);
        save_nfa.addActionListener(this);read_dfa.addActionListener(this);
        create_dfa.addActionListener(this);save_dfa.addActionListener(this);
        read_mfa.addActionListener(this);create_mfa.addActionListener(this);
        save_mfa.addActionListener(this);jTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                create_nfa.setEnabled(false);
            }
            @Override
            public void focusLost(FocusEvent e) {

            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==verify)Verify();
        else if(e.getSource()==reset)Reset();
        else if(e.getSource()==read_nfa)Read("nfa");
        else if(e.getSource()==create_nfa)nor2nfa();
        else if(e.getSource()==save_nfa)Save("nfa");
        else if(e.getSource()==read_dfa)Read("dfa");
        else if(e.getSource()==create_dfa)nfa2dfa();
        else if(e.getSource()==save_dfa)Save("dfa");
        else if(e.getSource()==read_mfa)Read("mfa");
        else if(e.getSource()==create_mfa)dfa2mfa();
        else if(e.getSource()==save_mfa)Save("mfa");
    }
    //验证
    void Verify(){
        char []chars=jTextField.getText().toCharArray();
        char before=' ',now;
        int length=chars.length,left=0;
        boolean legal=true;
        for (int i=0;i<length;i++){
            if(chars[i]!='\40'){
                before=chars[i];break;
            }
        }
        for(int i=0;i<length;i++){
            now=chars[i];
            if(chars[i]!='\40'&&!isLegal(chars[i])){
                if(now=='('){
                    left++;
                    if(!isLegal(before)){
                        legal=false;break;
                    }
                }else if(now==')'){
                    if(!isLegal(before)||left>1){
                        legal=false;break;
                    }
                    left--;
                }else if(now=='|'||now=='*'){
                    if(i==0||before=='('){
                        legal=false;break;
                    }
                }else{
                    legal=false;break;
                }
                before=now;
            }else if(isLegal(chars[i])){
                before=now;
            }
        }
        if(legal&&left==0){
            Notice("正确的输入");
            nor_str=jTextField.getText();
            create_nfa.setEnabled(true);
        }
        else Notice("非法的输入");
    }
    boolean isLegal(char now){
        if((now<='z'&&now>='a')||(now<='Z'&&now>='A')||(now<='9'&&now>='0'))return true;
        else return false;
    }
    //重置
    void Reset(){
        model_nfa.setRowCount(0);
        model_dfa.setRowCount(0);
        model_mfa.setRowCount(0);
        create_nfa.setEnabled(false);
        create_dfa.setEnabled(false);
        create_mfa.setEnabled(false);
    }
    //读入
    void Read(String string){
        model_nfa.setRowCount(0);
        if(string.equals("nfa")){
            nfa_str=Utils.OpenFile(fa_frame);
            if(Display(nfa_str)){
                Notice("读取成功");
                create_dfa.setEnabled(true);
            }
        }else if(string.equals("dfa")){
            dfa_str=Utils.OpenFile(fa_frame);
            if(Display(nfa_str)){
                Notice("读取成功");
                create_mfa.setEnabled(true);
            }
        }else{
            mfa_str=Utils.OpenFile(fa_frame);
            if(!mfa_str.isEmpty())Notice("读取成功");
        }
    }
    void Save(String string){
        if(string.equals("nfa")){
            if(Utils.SaveFile(fa_frame,dfa_str))Notice("保存成功");
        }else if(string.equals("dfa")){
            if(Utils.SaveFile(fa_frame,dfa_str))Notice("保存成功");
        }else{
            if(Utils.SaveFile(fa_frame,mfa_str))Notice("保存成功");
        }
    }
    boolean Display(String string){
        String []strings=string.split("\n");
        int len=strings.length;
        try{
            lab18.setText("初始状态集："+strings[0].substring(6));
            lab19.setText("终止状态集："+strings[1].substring(5));
            nfa_start=strings[0].substring(6).split("\40");
            nfa_end=strings[1].substring(5).split("\40");
            Sigma=strings[2].substring(6).split(";");
            for (int i=3;i<len;i++){
                String[] temp =strings[i].split("\t");
                Vector<String>vector=new Vector<>();
                vector.add(temp[0]);vector.add(temp[1]);vector.add(temp[2]);
                model_nfa.addRow(vector);
                nfa_list.add(new FaList(Integer.parseInt(temp[0]),temp[1].charAt(0),Integer.parseInt(temp[2])));
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }
    void Print_List(ArrayList<FaList> list,int option){
        for (int i=0;i<list.size();i++){
            FaList faList=list.get(i);
            Vector<String>vector=new Vector<>();
            vector.add(String.valueOf(faList.start));
            vector.add(String.valueOf(faList.receive));
            vector.add(String.valueOf(faList.end));
            if(option==1)model_nfa.addRow(vector);
            else if(option==2)model_dfa.addRow(vector);
            else if(option==3)model_mfa.addRow(vector);
        }
    }
    ArrayList<Integer> Move(ArrayList<Integer> array,char ch){
        ArrayList<Integer>res=new ArrayList<>();
        for (int j=0;j<array.size();j++){
            for(int i=0;i<nfa_list.size();i++){
                FaList temp=nfa_list.get(i);
                if(array.get(j)==temp.start&&ch==temp.receive){
                    res.add(temp.end);
                }
            }
        }
        return Set(res);
    }
    ArrayList<Integer> E_closure(ArrayList<Integer> array){
        int len=array.size(),list_len=nfa_list.size(),j;
        boolean isExist;
        ArrayList<Integer>result=new ArrayList<>();
        Queue<Integer>queue=new LinkedList<>();
        for (j=0;j<len;j++) {
            queue.offer(array.get(j));
            result.add(array.get(j));
        }
        while(!queue.isEmpty()){
            isExist=false;
            int front=queue.peek();
            for (j=0;j<list_len;j++){
                if(front==nfa_list.get(j).start&&nfa_list.get(j).receive=='#'){
                    isExist=true;
                    queue.offer(nfa_list.get(j).end);
                    result.add(nfa_list.get(j).end);
                }
            }
            queue.poll();
        }
        return Set(result);
    }
    //正则式到nfa
    void nor2nfa(){

    }
    //nfa到dfa
    void nfa2dfa(){
        model_dfa.setRowCount(0);
        ArrayList<ArrayList<Integer>>matrix=new ArrayList<>();
        ArrayList<Boolean>booleans=new ArrayList<>();
        ArrayList<Integer>start=str2int(nfa_start),end=str2int(nfa_end);
        matrix.add(E_closure(start));
        booleans.add(false);
        while (GetFalse(booleans)>-1){
            int index=GetFalse(booleans);
            for(int i=0;i<Sigma.length;i++){
                ArrayList<Integer>New=E_closure(Move(matrix.get(index),Sigma[i].charAt(0)));
                if (isContain(matrix,New)==-1){
                    matrix.add(New);booleans.add(false);
                }
            }
            booleans.set(index,true);
        }
        ArrayList<FaList>dfa_list=new ArrayList<>();
        for (int i=0;i<matrix.size();i++){
            for(int j=0;j<Sigma.length;j++){
                char ch=Sigma[j].charAt(0);
                if(isContain(matrix,E_closure(Move(matrix.get(i),ch)))!=-2){
                    FaList faList=new FaList(i+1,ch,isContain(matrix,E_closure(Move(matrix.get(i),ch)))+1);
                    dfa_list.add(faList);
                }
            }
        }
        Print_List(dfa_list,2);
        create_mfa.setEnabled(true);
    }
    void dfa2mfa(){

    }
    //string转array
    ArrayList<Integer> str2int(String[]strings){
        ArrayList<Integer>res=new ArrayList<>();
        for (int i=0;i<strings.length;i++){
            res.add(Integer.parseInt(strings[i]));
        }
        return res;
    }
    //删除重复元素
    ArrayList<Integer> Set(ArrayList<Integer> arrayList){
        ArrayList<Integer>set=new ArrayList<>();
        for (int j=0;j<arrayList.size();j++)
            if(!set.contains(arrayList.get(j)))set.add(arrayList.get(j));
        return set;
    }
    int GetFalse(ArrayList<Boolean> booleans){
        for (int i=0;i<booleans.size();i++)
            if (!booleans.get(i))return i;
        return -1;
    }
    void Notice(String str){
        JOptionPane.showMessageDialog(fa_frame,str,"提示",JOptionPane.INFORMATION_MESSAGE);
    }
    int isContain(ArrayList<ArrayList<Integer>> contain,ArrayList<Integer>element){
        if(element.size()==0)return -2;
        for (int i=0;i<contain.size();i++){
            if (contain.get(i).size()==element.size()){
                boolean is=false;
                for (Integer integer : element) {
                    if (!contain.get(i).contains(integer)) {
                        is = true;
                        break;
                    }
                }
                if(!is)return i;
            }
        }
        return -1;
    }
    public static void main(String []args){
        new NFA_DFA_MFA();
    }
}
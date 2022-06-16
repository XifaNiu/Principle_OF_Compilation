import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.*;

public class LR_Series extends JFrame implements ActionListener {
    // Variables declaration - do not modify
    private JTextArea Input_Area;
    private JTextField Sentence_Area;
    private JButton allDis_btn;
    private JButton ana_btn;
    private JButton confirm_btn;
    private JButton project_btn;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane4;
    private JScrollPane jScrollPane5;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JSeparator jSeparator4;
    private JSeparator jSeparator5;
    private JSeparator jSeparator6;
    private JButton oneDis_btn;
    private JButton open_file_btn;
    private JButton struct_btn;
    private JTable project_table;
    private JTable result_table;
    private JButton save_ana_btn;
    private JButton save_file_btn;
    private JButton select_btn;
    private JTable struct_table;
    //数据部分
    private String notice_str;
    private int GrammarType=-1;
    private ArrayList<LLNode> Node_List=new ArrayList<>(),Item_List=new ArrayList<>();//产生式、项目集合
    private ArrayList<ClusterNode>Cluster_List=new ArrayList<>();//簇集合
    private Map<Integer,ArrayList<ArrayList<Character>>> ClusterFollow=new HashMap<>();//用于保存每个项目集的follow集合
    private ArrayList<String>Vn=new ArrayList<>(),V=new ArrayList<>(),Vt=new ArrayList<>();
    private ArrayList<Vector<String>>Final=new ArrayList<>(),Save;//用来保存分析结果集合。
    private ArrayList<Integer>Conflict=new ArrayList<>();
    private Map<Integer,ArrayList<Character>>VtList=new HashMap<>();
    private Map<String,ArrayList<Character>>FollowMap;
    char dot='.',end='#';
    private DefaultTableModel Project_Model,Struct_Model,Result_Model;
    //结束
    // End of variables declaration
    public LR_Series() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        Project_Model=new DefaultTableModel();
        Struct_Model=new DefaultTableModel();
        Result_Model=new DefaultTableModel();
        jLabel1 = new JLabel();
        open_file_btn = new JButton();
        confirm_btn = new JButton();
        save_file_btn = new JButton();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jScrollPane1 = new JScrollPane();
        Input_Area = new JTextArea();
        project_btn = new JButton();
        select_btn = new JButton();
        jSeparator1 = new JSeparator();
        jLabel5 = new JLabel();
        jSeparator2 = new JSeparator();
        jScrollPane2 = new JScrollPane();
        project_table = new JTable();
        jLabel7 = new JLabel();
        jSeparator4 = new JSeparator();
        struct_btn = new JButton();
        jScrollPane4 = new JScrollPane();
        struct_table = new JTable();
        jLabel8 = new JLabel();
        jSeparator5 = new JSeparator();
        jLabel9 = new JLabel();
        Sentence_Area = new JTextField();
        ana_btn = new JButton();
        oneDis_btn = new JButton();
        allDis_btn = new JButton();
        save_ana_btn = new JButton();
        jLabel10 = new JLabel();
        jSeparator6 = new JSeparator();
        jScrollPane5 = new JScrollPane();
        result_table = new JTable();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LR自底向上分析(Series)");
        setPreferredSize(new java.awt.Dimension(1000, 650));

        jLabel1.setText("文法输入");

        open_file_btn.setText("打开文件");

        confirm_btn.setText("确认文法");

        save_file_btn.setText("保存文件");

        jLabel2.setText("注意事项:请输入满足LL(1)最简判别的2型文法。一行一个产生式");

        jLabel3.setText("注意事项:请输入形式如S->A的产生式，空格用_表示，空用$表示");

        jLabel4.setText("注意事项:开始符为第一个产生式的左部，非终结符用大写字母表示");

        Input_Area.setColumns(20);
        Input_Area.setRows(5);
        jScrollPane1.setViewportView(Input_Area);

        project_btn.setText("项目集规范族");

        select_btn.setText("选择LR分析类型");

        jLabel5.setText("项目集规范族");

        project_table.setModel(Project_Model);

        jScrollPane2.setViewportView(project_table);

        jLabel7.setText("分析表");

        struct_btn.setText("构造分析表");

        struct_table.setModel(Struct_Model);
        jScrollPane4.setViewportView(struct_table);

        jLabel8.setText("分析句子");

        jLabel9.setText("待分析句子：");

        ana_btn.setText("分析");

        oneDis_btn.setText("单步显示");

        allDis_btn.setText("一键显示");

        save_ana_btn.setText("保存结果");

        jLabel10.setText("分析结果");

        result_table.setModel(Result_Model);
        jScrollPane5.setViewportView(result_table);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel4, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                                        .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jSeparator1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(open_file_btn, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(confirm_btn, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(save_file_btn, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jSeparator2))
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(project_btn, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(select_btn)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jScrollPane1, GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane4, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jSeparator4))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jSeparator5))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addComponent(ana_btn, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(oneDis_btn, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(allDis_btn, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(save_ana_btn, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                .addGap(37, 37, 37))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel10, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jSeparator6))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(struct_btn)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(jScrollPane5)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(jLabel9)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Sentence_Area)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(jSeparator4, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)
                                        .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(open_file_btn)
                                        .addComponent(confirm_btn)
                                        .addComponent(save_file_btn)
                                        .addComponent(struct_btn))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel4)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane4, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(select_btn, GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jSeparator5, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel8)))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(project_btn)
                                                .addGap(1, 1, 1)))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel9)
                                                        .addComponent(Sentence_Area, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(ana_btn)
                                                        .addComponent(oneDis_btn)
                                                        .addComponent(allDis_btn)
                                                        .addComponent(save_ana_btn))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel10)
                                                        .addComponent(jSeparator6, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane5, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel5))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        pack();
        AddListener();
        ComponentControl();
    }// </editor-fold>
    //方法设置
    void Struct(){
        if (Vt.size()+Vn.size()>9) struct_table.setPreferredSize(new Dimension(124,26));
        if (GrammarType==0)Struct_LR_0_Table();
        else if(GrammarType==1)Struct_SLR_1_Table();
    }
    void Save_Ana(){
        StringBuilder res= new StringBuilder("步骤\t\t\t\t状态栈\t\t\t符号栈\t\t\t输入串\t\t\tACTION\t\t\tGOTO\n");
        if (Save.isEmpty()){
            Utils.Notice(this,"输入结果为空，无法保存");
            return;
        }
        for (int i=0;i<Save.size();i++){
            Vector<String> vector=Save.get(i);
            for (String s:vector){
                res.append(s).append("\t\t\t\t");
            }
            res.append("\n");
            Save.remove(i);i--;
        }
        res.append("分析结果:");
        if (notice_str.equals("接受，匹配成功"))res.append("分析完成，匹配成功");
        else if(notice_str.equals("失败，归约失败"))res.append("分析完成,匹配失败");
        Utils.SaveFile(this,res.toString());
    }
    void SenAnalyze(){
        Final.clear();
        Result_Model.setRowCount(0);
        Result_Model.setColumnCount(0);
        String sentence=Sentence_Area.getText();
        if (sentence.isEmpty())Utils.Notice(this,"输入句子为空，分析失败");
        else {
            int produce = 0;
            ArrayList<Integer> status = new ArrayList<>();//状态栈
            ArrayList<Character> chars = new ArrayList<>();
            ArrayList<String>symbol = new ArrayList<>();//符号栈和输入串
            //栈初始化
            status.add(0);
            symbol.add("#");
            for (char ch : sentence.toCharArray()) chars.add(ch);//将句子加入
            chars.add(end);
            while(true){
                boolean exit=false;
                Vector<String>vector=new Vector<>();
                produce++;
                //获取状态和输入串
                int now = status.get(status.size() - 1);//最后一个状态
                char ch = chars.get(0);
                int col = Utils.GetTableColumnIndex(Struct_Model, String.valueOf(ch));
                vector.add(String.valueOf(produce));
                vector.add(Utils.IntListToString(status));
                vector.add(Utils.StrListToString(symbol));
                vector.add(Utils.CharListToString(chars));
                String opt="";//获取操作
                if (col!=-1) opt = (String) Struct_Model.getValueAt(now,col);
                else {//未找到字符
                    vector.add("归约失败，不是合法的终结符");
                    exit=true;
                }
                if (opt.startsWith("S")){//移进项目
                    vector.add(opt);vector.add("");
                    //进行操作
                    status.add(Integer.valueOf(opt.substring(1)));//移入状态
                    symbol.add(String.valueOf(ch));
                    chars.remove(0);//删除第一个
                }else if (opt.startsWith("r")){//进行归约操作
                    //选择产生式
                    LLNode node=Node_List.get(Integer.parseInt(opt.substring(1)));
                    int right=node.V.length();
                    //从状态栈和符号栈中删除right个
                    for(int j = 0;j<right;j++){
                        status.remove(status.size()-1);
                        symbol.remove(symbol.size()-1);
                    }
                    //进行GOTO操作
                    col=Utils.GetTableColumnIndex(Struct_Model,node.Vn);
                    int next = Integer.parseInt(Struct_Model.getValueAt(status.get(status.size()-1),col).toString());
                    status.add(next);
                    symbol.add(node.Vn);
                    vector.add(opt);vector.add(String.valueOf(next));
                }else if (opt.equals("acc")){
                    vector.add("接受，匹配成功");
                    exit=true;
                }else if (col!=-1){
                    vector.add("失败，归约失败");
                    exit=true;
                }
                Final.add(vector);
                if (exit)break;
            }

            String []str={"步骤","状态栈","符号栈","输入串","ACTION","GOTO"};
            for(String s:str){
                Result_Model.addColumn(s);
            }//添加字符串
            oneDis_btn.setEnabled(true);
            allDis_btn.setEnabled(true);
            save_ana_btn.setEnabled(true);
            notice_str = Final.get(Final.size()-1).get(4);
            Save=new ArrayList<>(Final);
        }
    }
    void One_Show(){
        if (Final.isEmpty()){
            if (notice_str.equals("接受，匹配成功"))Utils.Notice(this,"分析完成，匹配成功");
            else if(notice_str.equals("失败，归约失败"))Utils.Notice(this,"分析完成,匹配失败");
            else Utils.Notice(this,"分析完成,非法的终结符");
        }else{
            Result_Model.addRow(Final.get(0));
            Final.remove(0);
        }
    }
    void Result_Show(){
        for (int i=0;i<Final.size();i++){
            Result_Model.addRow(Final.get(i));
            Final.remove(i);i--;
        }
        if (Final.isEmpty()){
            if (notice_str.equals("接受，匹配成功"))Utils.Notice(this,"分析完成，匹配成功");
            else if(notice_str.equals("失败，归约失败"))Utils.Notice(this,"分析完成,匹配失败");
            else Utils.Notice(this,"分析完成,非法的终结符");
        }
    }
    void Project_Show(){
        Project_Model.setRowCount(0);Project_Model.setColumnCount(0);
        Project_Model.addColumn("状态");Project_Model.addColumn("项目簇信息");
        project_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        project_table.getColumnModel().getColumn(0).setPreferredWidth(40);
        project_table.getColumnModel().getColumn(1).setPreferredWidth(412);
        for (ClusterNode cluster:Cluster_List){
            Vector<String>vector=new Vector<>();
            vector.add(String.valueOf(cluster.S));vector.add(Cluster2String(cluster.cluster));
            Project_Model.addRow(vector);
        }
    }
    void Open_File(){
        String str=Utils.OpenFile(this);
        if (!str.isEmpty()){
            Input_Area.setText(str);
            ComponentControl();
        }
        else Utils.Notice(this,"输入文件数据为空");
    }
    void Save_File(){
        if (Input_Area.getText().equals(""))Utils.Notice(this,"输入串为空，无法保存");
        else Utils.SaveFile(this,Input_Area.getText());
    }
    void Confirm(){
        if(Input_Area.getText().equals(""))Utils.Notice(this,"请输入文法");
        else if (GrammarType!=-1){
            StructItemCluster(GrammarType);
            Vn=Utils.GetVn(Node_List);
            V=Utils.GetV(Node_List);
            Vt=Utils.GetVt(Node_List);
        }
        switch (GrammarType){
            case 0:LR_0();break;
            case 1:SLR_1();break;
            default:Utils.Notice(this,"请先选择LR分析类型");SelectGrammar();Confirm();break;
        }
    }
    void LR_0(){
        boolean is=true;
        for (ClusterNode node:Cluster_List){
            boolean shift=false,rudece=false;
            for (LLNode n:node.cluster){
                int pos=n.V.indexOf(dot);
                if (pos==n.V.length()-1)rudece=true;
                else shift=true;
                if (shift&&rudece){
                    is=false;
                    break;
                }
            }
        }
        if (is){
            Utils.Notice(this,"是一个正确的LR(0)文法");
            struct_btn.setEnabled(true);
        }
        else {
            Utils.Notice(this,"是一个不正确的LR(0)文法");
            struct_btn.setEnabled(false);
        }
    }
    void Struct_LR_0_Table(){
        Struct_Model.setRowCount(0);Struct_Model.setColumnCount(0);
        //使用该方法，证明不存在移进-归约冲突
        //初始化列数据
        int rows=Project_Model.getRowCount();
        Struct_Model.addColumn("状态");
        for (String vt:Vt)Struct_Model.addColumn(vt);
        Struct_Model.addColumn("#");
        for (int i=1;i<Vn.size();i++)Struct_Model.addColumn(Vn.get(i));//建立完毕
        for (ClusterNode node : Cluster_List) {//遍历状态，添加行
            Vector<String>vector=new Vector<>();
            vector.add(String.valueOf(node.S));
            //获取接受字符串
            int pos=node.cluster.get(0).V.indexOf(dot);
            if (pos == node.cluster.get(0).V.length() - 1){//归约
                LLNode get=new LLNode(node.cluster.get(0));
                get.V=get.V.substring(0,get.V.length()-1);
                if (get.Vn.equals("E'")){//接受项目
                    for (String vt:Vt)vector.add("");
                    vector.add("acc");
                }else{//归约项目
                    for (int i=0;i<Node_List.size();i++){
                        if (Node_List.get(i).Vn.equals(get.Vn)&&Node_List.get(i).V.equals(get.V)){
                            for (String vt:Vt)vector.add("r"+i);
                            vector.add("r"+i);
                        }
                    }
                }
            }
            else{//移进
                for (String vt:Vt){   // 终结符
                    ArrayList<LLNode> tmp=GOTO(node.cluster, vt.charAt(0));
                    if (tmp.isEmpty())vector.add("");
                    else{
                        for (int i=0;i<rows;i++){
                            if (Cluster2String(tmp).equals(Project_Model.getValueAt(i,1))){
                                vector.add("S"+i);
                            }
                        }
                    }
                }
                vector.add("");
                //非终结符
                for (String vn:Vn){
                    if (vn.equals("E'"))continue;
                    ArrayList<LLNode> tmp=GOTO(node.cluster,vn.charAt(0));
                    if (tmp.isEmpty())vector.add("");
                    else{
                        for (int i=0;i<rows;i++){
                            if (Cluster2String(tmp).equals(Project_Model.getValueAt(i,1))){
                                vector.add(String.valueOf(i));
                            }
                        }
                    }
                }
            }
            Struct_Model.addRow(vector);
        }
        ana_btn.setEnabled(true);
    }
    void SLR_1(){
        VtList.clear();
        //求出归约项目的Follow集，通过LL_1类建造
        LL_1_Grammar ll1Grammar=new LL_1_Grammar(Input_Area.getText());
        FollowMap=ll1Grammar.FollowMap;
        ArrayList<Character>tt=new ArrayList<>();
        tt.add('#');
        FollowMap.put("E'",tt);
        boolean is=true;
        for (ClusterNode node:Cluster_List){
            int shift=0,rudece=0;
            boolean isExist=false;
            for (LLNode n:node.cluster){
                int pos=n.V.indexOf(dot);
                if (pos==n.V.length()-1){
                    rudece++;
                }
                else shift++;
                if ((shift>0&&rudece>0)||rudece>1){
                    isExist=true;
                    break;
                }
            }
            //判断是否可以用SLR方法解决
            if (isExist){//node存在冲突
                Conflict.add(node.S);//标记冲突状态，将存在归约的加入
                ArrayList<Character>res=new ArrayList<>();//用来保存结果，判断是否存在交集
                ArrayList<Character>tmp=new ArrayList<>();
                for (LLNode n:node.cluster){
                    int pos=n.V.indexOf(dot);
                    if (pos==n.V.length()-1){//归约项目,将非终结符加入
                        if (FollowMap.get(n.Vn)!=null) res.addAll(FollowMap.get(n.Vn));//follow集合加入
                        //如果是归约项目，则需要保存产生式和follow集合，通过follow集选择产生式子
                        //Map(state,LLNode,follow),再followA选择产生式A，因为不可能出现两个A，所以直接标注产生式序号
                    }else if (!Character.isUpperCase(n.V.charAt(pos+1))){//移入项目,且接受符号为终结符
                        res.add(n.V.charAt(pos+1));
                        tmp.add(n.V.charAt(pos+1));
                    }
                }
                //将终结符集合加入VtMap中
                VtList.put(node.S,tmp);//移进符号
                //判断是否存在交集
                if (ll1Grammar.isExistSame(res)){
                    is=false;break;
                }
            }
        }
        if (is){
            Utils.Notice(this,"是一个正确的SLR(1)文法");
            struct_btn.setEnabled(true);
        }
        else {
            Utils.Notice(this,"是一个不正确的SLR(1)文法");
            struct_btn.setEnabled(false);
        }
    }
    void Struct_SLR_1_Table(){
//        Struct_Model.setRowCount(0);Struct_Model.setColumnCount(0);
//        //使用该方法，证明不存在移进-归约冲突
//        //初始化列数据
//        //
//        Struct_Model.addColumn("状态");
//        for (String vt:Vt)Struct_Model.addColumn(vt);
//        Struct_Model.addColumn("#");
//        for (int i=1;i<Vn.size();i++)Struct_Model.addColumn(Vn.get(i));//建立完毕
//        for (ClusterNode node : Cluster_List) {//遍历状态，添加行
//            Vector<String>vector=new Vector<>();
//            vector.add(String.valueOf(node.S));
//            if (Conflict.contains(node.S)){//归约
//                //归约项目、存在冲突
//                //对于每个字符进行分析
//                for(String vt:Vt){
//                    char ch=vt.charAt(0);
//                    boolean red=true;
//                    //判断属于哪一个集合
//                    if (VtList.get(node.S)!=null&&VtList.get(node.S).contains(ch)){//在终结符中,进行移进操作
//                        ArrayList<LLNode> tmp=GOTO(node.cluster, vt.charAt(0));
//                        for (int i=0;i<Cluster_List.size();i++){
//                            if (Cluster2String(tmp).equals(Cluster2String(Cluster_List.get(i).cluster))){
//                                vector.add("S"+i);
//                                red=false;
//                            }
//                        }
//                    }
//                    else {//使用归约
//                        //判断属于哪个follow集合
//                        Map<Integer,ArrayList<Character>>Tmp=VnFollowList.get(node.S);
//                        for (Map.Entry<Integer, ArrayList<Character>> entry:Tmp.entrySet()){
//                            if(entry.getValue().contains(ch)){
//                                vector.add("r"+entry.getKey());
//                                red=false;
//                            }
//                        }
//                    }
//                    if (red){//都不存在
//                        vector.add("");
//                    }
//                }
//            }
//            else{//移进
//                for (String vt:Vt){   // 终结符
//                    ArrayList<LLNode> tmp=GOTO(node.cluster, vt.charAt(0));
//                    if (tmp.isEmpty())vector.add("");
//                    else{
//                        for (int i=0;i<Cluster_List.size();i++){
//                            if (Cluster2String(tmp).equals(Cluster2String(Cluster_List.get(i).cluster))){
//                                vector.add("S"+i);
//                            }
//                        }
//                    }
//                }
//                //非终结符
//                for (String vn:Vn){
//                    ArrayList<LLNode> tmp=GOTO(node.cluster, vn.charAt(0));
//                    if (tmp.isEmpty())vector.add("");
//                    else{
//                        for (int i=0;i<Cluster_List.size();i++){
//                            if (Cluster2String(tmp).equals(Cluster2String(Cluster_List.get(i).cluster))){
//                                vector.add(String.valueOf(i));
//                            }
//                        }
//                    }
//                }
//            }
//            Struct_Model.addRow(vector);
//        }
        //直接调用Struct_LR_0_Table
        Struct_LR_0_Table();
        //对存在冲突的归约进行修改
        int rows=Project_Model.getRowCount();
        LL_1_Grammar ll1Grammar=new LL_1_Grammar(Input_Area.getText());
        for (ClusterNode node:Cluster_List){//该状态存在冲突
            if (Conflict.contains(node.S)){
                System.out.println(node.S);
                //对每一列进行修改
                for (String vt:Vt){
                    int row=node.S;
                    int col=Vt.indexOf(vt)+1;
                    if (VtList.get(node.S).contains(vt.charAt(0))){//移进
                        ArrayList<LLNode> tmp=GOTO(node.cluster, vt.charAt(0));
                        for (int i=0;i<rows;i++){
                            if (Cluster2String(tmp).equals(Project_Model.getValueAt(i,1))){//找到了下一状态
                                Struct_Model.setValueAt("S"+i,row,col);
                            }
                        }
                    }
                }
            }
            for (String vt:Vt) {
                int row = node.S;
                int col = Vt.indexOf(vt) + 1;
                if (Struct_Model.getValueAt(row,col).toString().contains("S"))continue;
                //归约
                boolean is=true;
                for (LLNode n:node.cluster){
                    if(n.V.charAt(n.V.length()-1)==dot&&FollowMap.get(n.Vn).contains(vt.charAt(0))){//归约项目
                        for (int i=0;i<Node_List.size();i++){//找到产生式位置
                            if (Node_List.get(i).Vn.equals(n.Vn)&&n.V.equals(Node_List.get(i).V+dot)){
                                Struct_Model.setValueAt("r"+i,row,col);
                                is=false;
                                break;
                            }
                        }
                        if(!is)break;
                    }
                }
                if (is)Struct_Model.setValueAt("",row,col);
            }

        }
        ana_btn.setEnabled(true);
    }
    LRNode LRClosure(ArrayList<LLNode>list,ArrayList<ArrayList<Character>>follow,LL_1_Grammar grammar){//集合以及序号。
        //遍历数组，如果.的后面是非终结符，就将终结符的以.开头的加入
        ArrayList<LLNode>result=new ArrayList<>(list);
        Queue<LLNode> queue=new LinkedList<>(list);
        Queue<ArrayList<Character>> queue_follow=new LinkedList<>(follow);
        while (!queue.isEmpty()){
            LLNode node=queue.poll();
            ArrayList<Character>follow_c=queue_follow.poll();//当前后后面的字符
            int pos=node.V.indexOf(dot);
            if (pos==node.V.length()-1)continue;
            if (Character.isUpperCase(node.V.charAt(pos+1))){
                //待约项目，获取后跟字符串的first集
                boolean isCanNull=true;
                ArrayList<Character> tmp=new ArrayList<>();
                if (!node.V.substring(pos+2).isEmpty()){
                    tmp=grammar.First(node.V.substring(pos+2));
                    isCanNull=grammar.Str_Null(node.V.substring(pos+2));
                }
                for (LLNode n:Item_List){
                    if (node.V.charAt(node.V.indexOf(dot)+1)==n.Vn.toCharArray()[0]
                            &&!n.Vn.contains("E'") &&n.V.startsWith(".")){//.开头的项目加入
                        ArrayList<Character> f_tmp = new ArrayList<>(tmp);//一定包含first
                        if (isCanNull){f_tmp.addAll(follow_c);}
                        if (isContainer(result, n)){//如果不包含节点
                            if (n.V.equals(".$")){//.$的清空，视为规约项目，不将其加入队列
                                result.add(new LLNode(n.Vn,"$."));
                                follow.add(f_tmp);
                            }else{
                                result.add(new LLNode(n));
                                follow.add(f_tmp);
                                queue.add(new LLNode(n));
                                queue_follow.add(new ArrayList<>(f_tmp));
                            }
                        }
                    }
                }
            }
        }
        LRNode ret=new LRNode(result,follow);
        return ret;
    }
    void StructItemCluster(int GrammarType){
        Node_List=GetNodeList(Input_Area.getText());//拓广文法
        Item_List.clear();
        Cluster_List.clear();
        //构造项目集
        for (LLNode node:Node_List){
            int len=node.V.length();
            for (int i=0;i<len;i++){
                Item_List.add(new LLNode(node.Vn, node.V.substring(0,i)+"."+node.V.substring(i,len)));
            }
            Item_List.add(new LLNode(node.Vn,node.V+"."));
        }
        if (GrammarType==2||GrammarType==3){
            LL_1_Grammar grammar=new LL_1_Grammar(Input_Area.getText());
            //如何实现后面跟follow集，
            //初始的集合
            ArrayList<ArrayList<Character>>tmp=new ArrayList<>();
            ArrayList<Character> one=new ArrayList<>();
            ArrayList<LLNode>t=new ArrayList<>();
            one.add(end);
            tmp.add(one);
            t.add(Item_List.get(0));//初始集
            LRNode node=LRClosure(t,tmp,grammar);
            for (ArrayList<Character> chs:node.follow){
                System.out.println(chs);
            }
            for (LLNode nodes:node.list){
                System.out.println(nodes.Vn+"->"+nodes.V);
            }
            //本质上就是要狗仔出first(follow)即可，并使其对应即可，GOTO不更换
            return;
        }
        //构造第一个cluster
        int index=0;
        ArrayList<LLNode>tmp=new ArrayList<>();
        tmp.add(Item_List.get(0));//初始集
        Cluster_List.add(new ClusterNode(index,Closure(tmp)));//加入到Cluster集合中
//        System.out.println(GetChar(Closure(tmp)));//测试GetChar是否正确
//        for(LLNode node:GOTO(Closure(tmp),'T'))System.out.println(node.Vn+"->"+node.V);
        Queue<ArrayList<LLNode>>queue=new LinkedList<>();
        queue.add(Closure(tmp));
        while(!queue.isEmpty()){
            ArrayList<LLNode>cluster=queue.poll();//获取未产生的簇
            ArrayList<Character>chars=GetChar(cluster);//获取输入符号
            //对所有符号进行运算
            for(char ch:chars){
                ArrayList<LLNode>next=GOTO(cluster,ch);
                if (!isContain(Cluster_List,new ClusterNode(index,next))){
                    index++;
                    Cluster_List.add(new ClusterNode(index,next));
                    queue.offer(next);
                }
            }
        }
        project_btn.setEnabled(true);
    }
    boolean isContain(ArrayList<ClusterNode>source,ClusterNode element){
        for (ClusterNode node:source){
            if (Cluster2String(node.cluster).equals(Cluster2String(element.cluster))){
                return true;
            }
        }
        return false;
    }
    ArrayList<LLNode> Closure(ArrayList<LLNode> list){
        //遍历数组，如果.的后面是非终结符，就将终结符的以.开头的加入
        ArrayList<LLNode>result=new ArrayList<>(list);
        Queue<LLNode>queue=new LinkedList<>(list);
        while (!queue.isEmpty()){
            LLNode node=queue.poll();
            if (node.V.indexOf(dot)==node.V.length()-1)continue;
            if (Character.isUpperCase(node.V.charAt(node.V.indexOf(dot)+1))){
                for (LLNode n:Item_List){
                    if (node.V.charAt(node.V.indexOf(dot)+1)==n.Vn.toCharArray()[0]
                            &&!n.Vn.contains("E'") &&n.V.startsWith(".")){//.开头的项目加入
                        if (isContainer(result, n)){//如果不包含节点
                            if (n.V.equals(".$")){//.$的清空，视为规约项目，不将其加入队列
                                result.add(new LLNode(n.Vn,"$."));
                            }else{
                                result.add(new LLNode(n));
                                queue.add(new LLNode(n));
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
    String Cluster2String(ArrayList<LLNode>source){
        StringBuilder stringBuilder=new StringBuilder();
        for (LLNode node:source)
            stringBuilder.append(node.Vn).append("->").append(node.V).append("  ");
        return stringBuilder.toString();
    }
    ArrayList<LLNode> GetNodeList(String source){
        String[]strings=source.split("\n");
        String str="E'->"+strings[0].split("->")[0]+"\n"+source;
        strings=str.split("\n");
        ArrayList<LLNode>result=new ArrayList<>();
        for (String string : strings) {
            String Vn = string.split("->")[0];
            String[] Vs = string.split("->")[1].split("\\|");
            for (String v : Vs) {
                LLNode node = new LLNode(Vn, v);
                result.add(node);
            }
        }

        return result;
    }
    ArrayList<LLNode> GOTO(ArrayList<LLNode> source,char receive){
        ArrayList<LLNode>next=new ArrayList<>();
        for (LLNode node:source){
            String v=node.V;
            int pos=node.V.indexOf(dot);//点的位置
            if (pos<v.length()-1&&v.charAt(pos+1)==receive){
                LLNode add=new LLNode(node.Vn,v.substring(0,pos)+v.charAt(pos+1)+"."+v.substring(pos+2));
                if (isContainer(next, add)){
                    next.add(new LLNode(add));
                }
            }
        }
        return Closure(next);
    }
    ArrayList<Character> GetChar(ArrayList<LLNode>source){
        ArrayList<Character>res=new ArrayList<>();
        for (LLNode node:source){
            int pos=node.V.indexOf(dot);
            if (pos<node.V.length()-1){
                char ch=node.V.charAt(node.V.indexOf(dot)+1);//.之后的字符
                if (!res.contains(ch))res.add(ch);
            }
        }
        return res;
    }
    boolean isContainer(ArrayList<LLNode>source, LLNode element){
        for (LLNode node:source){
            if (node.Vn.equals(element.Vn)&&node.V.equals(element.V))return false;
        }
        return true;
    }
    void SelectGrammar(){
        int index=JOptionPane.showOptionDialog(this,"请选择对应的文法","选择文法",JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,new String[]{"LR(0)","SLR(1)"},-1);
        //对界面进行一些修改
        switch (index){
            case 0: Modify("LR(0)");break;
            case 1: Modify("SLR(1)");break;
            case 2: Modify("LR(1)");break;
            case 3: Modify("LALR(1)");break;
            default:Modify("");break;
        }
        GrammarType=index;
    }
    void Modify(String str){
        this.setTitle("LR自底向上分析(Series)  当前为："+str);
        jLabel7.setText(str+"分析表");
        struct_btn.setText("构造"+str+"分析表");
        if (str.equals("")) jLabel2.setText("注意事项:请输入满足LR(Series)最简判别的2型文法。一行一个产生式");
        else jLabel2.setText("注意事项:请输入满足"+str+"最简判别的2型文法。一行一个产生式");
    }
    void ComponentControl(){
        project_btn.setEnabled(false);
        select_btn.setEnabled(true);
        struct_btn.setEnabled(false);
        ana_btn.setEnabled(false);
        oneDis_btn.setEnabled(false);
        allDis_btn.setEnabled(false);
        save_ana_btn.setEnabled(false);
    }
    //设置结束
    //监听设置
    void AddListener(){
        open_file_btn.addActionListener(this);
        confirm_btn.addActionListener(this);
        save_file_btn.addActionListener(this);
        project_btn.addActionListener(this);
        select_btn.addActionListener(this);
        struct_btn.addActionListener(this);
        ana_btn.addActionListener(this);
        allDis_btn.addActionListener(this);
        oneDis_btn.addActionListener(this);
        save_ana_btn.addActionListener(this);
        Sentence_Area.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                oneDis_btn.setEnabled(false);
                allDis_btn.setEnabled(false);
                save_ana_btn.setEnabled(false);
            }
            @Override
            public void focusLost(FocusEvent e) { }
        });
        Input_Area.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                ComponentControl();
            }
            @Override
            public void focusLost(FocusEvent e) {}
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==open_file_btn)Open_File();
        else if (e.getSource()==confirm_btn)Confirm();
        else if (e.getSource()==save_file_btn)Save_File();
        else if (e.getSource()==project_btn)Project_Show();
        else if (e.getSource()==select_btn)SelectGrammar();
        else if (e.getSource()==struct_btn)Struct();
        else if (e.getSource()==ana_btn)SenAnalyze();
        else if (e.getSource()==oneDis_btn)One_Show();
        else if (e.getSource()==allDis_btn)Result_Show();
        else if (e.getSource()==save_ana_btn)Save_Ana();
    }
    //设置结束
}

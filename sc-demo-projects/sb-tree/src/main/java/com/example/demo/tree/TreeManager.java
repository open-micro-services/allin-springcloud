package com.example.demo.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @ClassName: TreeManager
 * @Description: TODO(树结构数据管理-实践验证)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2019-06-18 23:47
 */
public class TreeManager {

    /**
     * 将List转成tree结构数据
     * @param list
     * @param rootId 默认顶级节点ID
     * @return
     */
    public static List<TreeNode> listToTree(List<TreeNode> list,long rootId){
        List<TreeNode> tree=new ArrayList<TreeNode>();
        Map<Long, TreeNode> map = new HashMap<Long, TreeNode>();
        // 将所有的数据，以键值对的形式装入map中
        for (TreeNode node : list) {
            // 去除冗余的子节点
            node.setChildren(new ArrayList<TreeNode>());
            map.put(node.getId(), node);
        }
        for (TreeNode node : list) {
            // 如果id是父级的话就放入tree中
            if (node.getId() == rootId) {
                tree.add(node);
            } else {
                // 子级通过父id获取到父级的类型
                TreeNode parent = map.get(node.getPid());
                // 父级获得子级，再将子级放到对应的父级中
                if(parent!=null){
                    parent.getChildren().add(node);
                }
            }
        }
        return tree;
    }

    /**
     * 将tree结构数据转成List结构
     * @param list
     * @return
     */
    public static void treeToList(TreeNode node,List<TreeNode> list){
        if(list==null){
            list=new ArrayList<TreeNode>();
        }
        //设置当前节点的必要数据
        TreeNode nodeValue=new TreeNode();
        nodeValue.setId(node.getId());
        nodeValue.setLabel(node.getLabel());
        nodeValue.setValue(node.getValue());
        nodeValue.setMultiValues(node.getMultiValues());
        nodeValue.setChildrenMultiValues(node.getChildrenMultiValues());
        nodeValue.setPid(node.getPid());
        nodeValue.setChildren(new ArrayList<TreeNode>());
        list.add(nodeValue);
        //遍历递归子节点
        if(node.getChildren().size()>0){
            for (int i = 0; i < node.getChildren().size(); i++) {
                TreeNode node_= node.getChildren().get(i);
                treeToList(node_,list);
            }
        }
    }

    /**
     * 转换数据格式并设置对应节点的值汇总到根节点
     * @param list
     * @param rootId
     * @return
     */
    public static List<TreeNode> listToTreeWithSingleValue(List<TreeNode> list,long rootId){
        Map<Long, TreeNode> map = new HashMap<Long, TreeNode>();
        // 将所有的数据，以键值对的形式装入map中
        for (TreeNode node : list) {
            // 去除冗余的子节点
            node.setChildren(new ArrayList<TreeNode>());
            map.put(node.getId(), node);
        }
        List<TreeNode> tree=listToTree(list,rootId);
       /* // 存储最小子节点ID
        Map<Long,Object> leafList=new  HashMap<Long,Object>();
        findMinNodes(tree.get(0),leafList,0);
        // 设置每个节点的值
        for (Long id_: leafList.keySet()) {
            // 内部递归树的父节点层级多于2会存在重复计算
            setParentNodeValue(map,id_);
        }*/

        // 存储最小子节点ID
        Map<Long,Object> leaf=new  HashMap<Long,Object>();
        findMinNodes(tree.get(0),leaf);
        // 逐级设置父节点的值
        setValuesToParentNode(leaf, map);

        // 汇总所有节点的值
        double total=0;
        for (TreeNode node:map.values() ) {
            total=0;
            for (double value: node.getValues() ) {
                total+=value;
            }
            node.setValue(total);
            map.put(node.getId(),node);
        }
        List<TreeNode>  result=new ArrayList<TreeNode>();
        for (TreeNode node:map.values()) {
            result.add(node);
        }
        return listToTree(result,rootId);
    }

    /**
     * 转换数据格式并设置对应节点的值汇总到根节点
     * @param tree
     * @return
     */
    public static  List<TreeNode>   treeToListWithSingleValue(TreeNode tree){
        List<TreeNode> list=new ArrayList<TreeNode>();
        // 获取到List
        treeToList(tree,list);
        Map<Long, TreeNode> map = new HashMap<Long, TreeNode>();
        // 将所有的数据，以键值对的形式装入map中
        for (TreeNode node : list) {
            // 去除冗余的子节点
            node.setChildren(new ArrayList<TreeNode>());
            map.put(node.getId(), node);
        }
       /* // 存储最小子节点ID
        Map<Long,Object> leafList=new  HashMap<Long,Object>();
        findMinNodes(tree,leafList,0);
        // 设置每个节点的值
        for (Long id_: leafList.keySet()) {
            // 内部递归树的父节点层级多于2会存在重复计算
            setParentNodeValue(map,id_);
        }*/

        // 存储最小子节点ID
        Map<Long,Object> leaf=new  HashMap<Long,Object>();
        findMinNodes(tree,leaf);
        // 逐级设置父节点的值
        setValuesToParentNode(leaf, map);

        // 汇总所有节点的值
        double total=0;
        for (TreeNode node:map.values() ) {
            total=0;
            for (double value: node.getValues() ) {
                total+=value;
            }
            node.setValue(total);
            map.put(node.getId(),node);
        }
        List<TreeNode>  result=new ArrayList<TreeNode>();
        for (TreeNode node:map.values()) {
            result.add(node);
        }
        return result;
    }

    /**
     * 转换数据格式并设置对应节点的值汇总到根节点
     * @param list
     * @param rootId
     * @param columns
     * @return
     */
    public static List<TreeNode> listToTreeWithMultiValues(List<TreeNode> list,long rootId,int columns){
        Map<Long, TreeNode> map = new HashMap<Long, TreeNode>();
        // 将所有的数据，以键值对的形式装入map中
        for (TreeNode node : list) {
            // 去除冗余的子节点
            node.setChildren(new ArrayList<TreeNode>());
            map.put(node.getId(), node);
        }
        List<TreeNode> tree=listToTree(list,rootId);

       /* // 存储最小子节点ID
        Map<Long,Object> leafList=new  HashMap<Long,Object>();
        findMinNodes(tree.get(0),leafList,0);
        // 设置每个节点的值
        for (Long id_: leafList.keySet()) {
            // 内部递归树的父节点层级多于2会存在重复计算
            setParentNodeMultiValues(map,id_);
        }*/

        // 存储最小子节点ID
        Map<Long,Object> leaf=new  HashMap<Long,Object>();
        findMinNodes(tree.get(0),leaf);
        // 逐级追加父节点的值
        setMultiValuesToParentNode(leaf, map);

        // 汇总所有节点的值
        double [] valueColumns=null;
        for (TreeNode node:map.values() ) {
            valueColumns=new double[columns];
            for (double [] values: node.getChildrenMultiValues() ) {
                for (int i = 0,j=values.length; i < j; i++) {
                    valueColumns[i]+=values[i];
                }
            }
            node.setMultiValues(valueColumns);
            map.put(node.getId(),node);
        }
        List<TreeNode>  result=new ArrayList<TreeNode>();
        for (TreeNode node:map.values()) {
            result.add(node);
        }
        return listToTree(result,rootId);
    }

    /**
     * 转换数据格式并设置对应节点的值汇总到根节点
     * @param tree
     * @param columns
     * @return
     */
    public static  List<TreeNode>  treeToListWithMultiValues(TreeNode tree,int columns){
        List<TreeNode> list=new ArrayList<TreeNode>();
        // 获取到List
        treeToList(tree,list);
        Map<Long, TreeNode> map = new HashMap<Long, TreeNode>();
        // 将所有的数据，以键值对的形式装入map中
        for (TreeNode node : list) {
            // 去除冗余的子节点
            node.setChildren(new ArrayList<TreeNode>());
            map.put(node.getId(), node);
        }
        /*
        // 存储最小子节点ID
        Map<Long,Object> leafList=new  HashMap<Long,Object>();
        findMinNodes(tree,leafList,0);
        // 设置每个节点的值
        for (Long id_: leafList.keySet()) {
             // 内部递归树的父节点层级多于2会存在重复计算
            setParentNodeMultiValues(map,id_);
        }*/

        // 存储最小子节点ID
        Map<Long,Object> leaf=new  HashMap<Long,Object>();
        findMinNodes(tree,leaf);
        // 逐级追加父节点的值
        setMultiValuesToParentNode(leaf, map);

        // 汇总所有节点的值
        double [] valueColumns=null;
        for (TreeNode node:map.values() ) {
            valueColumns=new double[columns];
            for (double [] values: node.getChildrenMultiValues() ) {
                for (int i = 0,j=values.length; i < j; i++) {
                    valueColumns[i]+=values[i];
                }
            }
            node.setMultiValues(valueColumns);
            map.put(node.getId(),node);
        }
        List<TreeNode>  result=new ArrayList<TreeNode>();
        for (TreeNode node:map.values()) {
            result.add(node);
        }
        return result;
    }

    /**
     * 逐级追加设置节点的值（单个值）
     * @param leaf
     * @param map
     */
    public static void setValuesToParentNode(Map<Long,Object> leaf,Map<Long, TreeNode> map){
        Map<Long,Object> newLeaf=new  HashMap<Long,Object>();
        // 设置每个节点的值
        for (Long id_: leaf.keySet()) {
            setParentNodeValue(newLeaf,map,id_);
        }
        if(newLeaf.size()>1){
            setValuesToParentNode(newLeaf, map);
        }
    }

    /**
     * 逐级追加设置节点的值（多个值）
     * @param leaf
     * @param map
     */
    public static void setMultiValuesToParentNode( Map<Long,Object> leaf,Map<Long, TreeNode> map){
        Map<Long,Object> newLeaf=new  HashMap<Long,Object>();
        // 设置每个节点的值
        for (Long id_: leaf.keySet()) {
            setParentNodeMultiValues(newLeaf,map,id_);
        }
        if(newLeaf.size()>1){
            setMultiValuesToParentNode(newLeaf, map);
        }
    }

    /**
     * 数学运算
     * @param mathChar
     * @param dest
     * @param newValue
     */
    public static void mathHandle(String mathChar,double dest,double newValue){
        switch (mathChar) {
            case "+":
                dest+=newValue;
                break;
            case "-":
                dest-=newValue;
                break;
            case "*":
                dest*=newValue;
                break;
            case "/":
                dest/=newValue;
                break;
            default:
                break;
        }
    }

    /**
     * 查找最小子叶节点（没有子节点的节点）
     * @param node
     * @param leafList
     */
    private static void findMinNodes(TreeNode node,Map<Long,Object> leafList){
        if(node.getChildren().size()>0){
            TreeNode nodeTmp=null;
            for (int i = 0; i < node.getChildren().size(); i++) {
                nodeTmp= node.getChildren().get(i);
                findMinNodes(nodeTmp,leafList);
            }
        }else{
            leafList.put(node.getId(),node.getId());
        }
    }

    /**
     * 根据ID逐级查找父节点并设置值(设置单个值逐级递归)
     * @param map
     * @param id
     */
    private static void setParentNodeValue(Map<Long,Object> newLeaf,Map<Long, TreeNode> map,long id){
        TreeNode node=map.get(id);
        // 设置自身节点的值
        if(!node.isAddSelf()){
            node.setAddSelf(true);
            node.getValues().add(node.getValue());
            // 更新节点数据
            map.put(node.getId(),node);
        }
        TreeNode pNode=map.get(node.getPid());
        if(pNode!=null){
            // 将子节点的值赋给父节点
            pNode.getValues().addAll(node.getValues());
            // 设置自身节点的值
            if(!pNode.isAddSelf()){
                pNode.setAddSelf(true);
                pNode.getValues().add(pNode.getValue());
            }
            // 更新节点数据
            map.put(pNode.getId(),pNode);
            //setParentNodeValue(map,pNode.getId());
            newLeaf.put(pNode.getId(), pNode.getId());
        }
    }

    /**
     * 根据ID逐级查找父节点并设置值(设置多个值逐级递归)
     * @param map
     * @param id
     */
    private static void setParentNodeMultiValues(Map<Long,Object> newLeaf,Map<Long, TreeNode> map,long id){
        TreeNode node=map.get(id);
        // 设置自身节点的值
        if(!node.isAddSelf()){
            node.setAddSelf(true);
            node.getChildrenMultiValues().add(node.getMultiValues());
            // 更新节点数据
            map.put(node.getId(),node);
        }
        TreeNode pNode=map.get(node.getPid());
        if(pNode!=null){
            // 将子节点的值赋给父节点
            pNode.getChildrenMultiValues().addAll(node.getChildrenMultiValues());
            // 设置自身节点的值
            if(!pNode.isAddSelf()){
                pNode.setAddSelf(true);
                pNode.getChildrenMultiValues().add(pNode.getMultiValues());
            }
            // 更新节点数据
            map.put(pNode.getId(),pNode);
            //setParentNodeMultiValues(map,pNode.getId());
            newLeaf.put(pNode.getId(), pNode.getId());
        }
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        TreeNode tree=new TreeNode();
        tree.setId(1);
        tree.setLabel("顶层节点");
        tree.setValue(1);
        tree.setChildrenMultiValues(new ArrayList<double []>());
        tree.setPid(0);

        List<TreeNode> list =new ArrayList<TreeNode>();
        TreeNode node1=new TreeNode();
        node1.setId(2);
        node1.setLabel("子节点1");
        node1.setValue(100);
        node1.setMultiValues(new double[]{5,7,3});
        node1.setChildrenMultiValues(new ArrayList<double []>());
        node1.setPid(1);
        list.add(node1);
        TreeNode node2=new TreeNode();
        node2.setId(3);
        node2.setLabel("子节点2");
        node2.setValue(10);
        node2.setMultiValues(new double[]{2,5,8});
        node2.setChildrenMultiValues(new ArrayList<double []>());
        node2.setPid(1);
        list.add(node2);

        tree.setChildren(list);

        List<TreeNode> destList=new ArrayList<TreeNode>();
        TreeManager.treeToList(tree,destList);
        System.out.println("tree转list完成");

        List<TreeNode> treeList=TreeManager.listToTree(destList,1);

        System.out.println("List转tree完成");

        /*******************注意单个值和多个值之间存在互斥关系==下面注释就是为了不影响结果**************/

        List<TreeNode> treeListSingleValue=TreeManager.listToTreeWithSingleValue(destList,1);
        System.out.println("List转tree 汇总唯一值value完成");

        List<TreeNode> treeListSingleValue2=TreeManager.treeToListWithSingleValue(tree);
        System.out.println("tree转List 汇总唯一值value完成");

//        List<TreeNode> treeListMultiValues=TreeManager.listToTreeWithMultiValues(destList,1,3);
//        System.out.println("List转tree 汇总多个值values完成");
//
//        List<TreeNode> treeListMultiValues2=TreeManager.treeToListWithMultiValues(tree,3);
//        System.out.println("tree转List 汇总多个值values完成");

    }
}

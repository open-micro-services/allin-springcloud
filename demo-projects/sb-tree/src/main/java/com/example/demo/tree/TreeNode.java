package com.example.demo.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TreeNode
 * @Description: TODO(树的节点对象)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2019-06-18 23:35
 */
public class TreeNode {

    /**
     * 节点ID
     */
    private long id;
    /**
     * 显示名称
     */
    private String label;
    /**
     * 当前节点的唯一值
     */
    private double value;
    /**
     * 当前节点的多个值的表达方式
     */
    private double[] multiValues=new double[]{};
    /**
     * 汇总单个节点的多个值
     */
    private List<Double> values=new ArrayList<Double>();
    /**
     * 当前节点所有子节点的值集合
     */
    private List<double []> childrenMultiValues=new ArrayList<double []>();
    /**
     * 父节点ID
     */
    private long pid;
    /**
     * 子节点集合对象
     */
    private List<TreeNode> children=new ArrayList<TreeNode>();
    /**
     * 是否计算本身
     */
    private boolean addSelf=false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double[] getMultiValues() {
        return multiValues;
    }

    public void setMultiValues(double[] multiValues) {
        this.multiValues = multiValues;
    }

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }

    public List<double[]> getChildrenMultiValues() {
        return childrenMultiValues;
    }

    public void setChildrenMultiValues(List<double[]> childrenMultiValues) {
        this.childrenMultiValues = childrenMultiValues;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public boolean isAddSelf() {
        return addSelf;
    }

    public void setAddSelf(boolean addSelf) {
        this.addSelf = addSelf;
    }
}

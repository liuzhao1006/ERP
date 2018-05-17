package com.ycjt.sx.erp.maillist.bean.json;

import com.ycjt.sx.base.BaseBean;

/**
 * Created by liuchao on 2017/6/28.
 */

public class MaillistDataJson extends BaseBean {

    /**
     * ID : 04f12beb-d99d-43df-ac9a-3042957d6bda
     * Name : 广州亿程
     * Number : 6
     * Type : 1
     * Status : 0
     * ParentID : 00000000-0000-0000-0000-000000000000
     * Sort : 1
     * Depth : 0
     * ChildsLength : 8
     * ChargeLeader : null
     * Leader : null
     * Note : null
     */

    private String ID;
    private String Name;
    private String Number;
    private int Type;
    private int Status;
    private String ParentID;
    private int Sort;
    private int Depth;
    private int ChildsLength;
    private Object ChargeLeader;
    private Object Leader;
    private Object Note;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getParentID() {
        return ParentID;
    }

    public void setParentID(String ParentID) {
        this.ParentID = ParentID;
    }

    public int getSort() {
        return Sort;
    }

    public void setSort(int Sort) {
        this.Sort = Sort;
    }

    public int getDepth() {
        return Depth;
    }

    public void setDepth(int Depth) {
        this.Depth = Depth;
    }

    public int getChildsLength() {
        return ChildsLength;
    }

    public void setChildsLength(int ChildsLength) {
        this.ChildsLength = ChildsLength;
    }

    public Object getChargeLeader() {
        return ChargeLeader;
    }

    public void setChargeLeader(Object ChargeLeader) {
        this.ChargeLeader = ChargeLeader;
    }

    public Object getLeader() {
        return Leader;
    }

    public void setLeader(Object Leader) {
        this.Leader = Leader;
    }

    public Object getNote() {
        return Note;
    }

    public void setNote(Object Note) {
        this.Note = Note;
    }

    @Override
    public String toString() {
        return "MaillistDataJson{" +
                "ID='" + ID + '\'' +
                ", Name='" + Name + '\'' +
                ", Number='" + Number + '\'' +
                ", Type=" + Type +
                ", Status=" + Status +
                ", ParentID='" + ParentID + '\'' +
                ", Sort=" + Sort +
                ", Depth=" + Depth +
                ", ChildsLength=" + ChildsLength +
                ", ChargeLeader=" + ChargeLeader +
                ", Leader=" + Leader +
                ", Note=" + Note +
                '}';
    }
}

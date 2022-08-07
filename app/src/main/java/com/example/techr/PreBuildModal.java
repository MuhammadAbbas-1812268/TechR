package com.example.techr;

public class PreBuildModal {

    private String CPU;
    private String CPUCooler;
    private String Memory;
    private String Monitor;
    private String Motherboard;
    private String PowerSupply;
    private String Storage;
    private String Total;

    public PreBuildModal(){

    }

    public PreBuildModal(String CPU, String CPUCooler, String memory, String monitor, String motherboard, String powerSupply, String storage, String videoCard, String total) {
        this.CPU = CPU;
        this.CPUCooler = CPUCooler;
        this.Memory = memory;
        this.Monitor = monitor;
        this.Motherboard = motherboard;
        this.PowerSupply = powerSupply;
        this.Storage = storage;
        this.VideoCard = videoCard;
        this.Total = total;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getCPUCooler() {
        return CPUCooler;
    }

    public void setCPUCooler(String CPUCooler) {
        this.CPUCooler = CPUCooler;
    }

    public String getMemory() {
        return Memory;
    }

    public void setMemory(String memory) {
        Memory = memory;
    }

    public String getMonitor() {
        return Monitor;
    }

    public void setMonitor(String monitor) {
        Monitor = monitor;
    }

    public String getMotherboard() {
        return Motherboard;
    }

    public void setMotherboard(String motherboard) {
        Motherboard = motherboard;
    }

    public String getPowerSupply() {
        return PowerSupply;
    }

    public void setPowerSupply(String powerSupply) {
        PowerSupply = powerSupply;
    }

    public String getStorage() {
        return Storage;
    }

    public void setStorage(String storage) {
        Storage = storage;
    }

    public String getVideoCard() {
        return VideoCard;
    }

    public void setVideoCard(String videoCard) {
        VideoCard = videoCard;
    }

    private String VideoCard;

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

}

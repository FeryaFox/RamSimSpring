package ru.feryafox.os_lab1_spring.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ram_sims")
public class RamSimEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int ramSize;

    @Column(nullable = false)
    private int blockSize;

    @Column(nullable = false, unique = true)
    private String uuid;

    @Lob
    @Column(nullable = false)
    private byte[] ram;

    public RamSimEntity(int ramSize, int blockSize, String uuid, byte[] ram) {
        this.ramSize = ramSize;
        this.blockSize = blockSize;
        this.uuid = uuid;
        this.ram = ram;
    }

    public RamSimEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRamSize() {
        return ramSize;
    }

    public void setRamSize(int ramSize) {
        this.ramSize = ramSize;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public byte[] getRam() {
        return ram;
    }

    public void setRam(byte[] ram) {
        this.ram = ram;
    }
}
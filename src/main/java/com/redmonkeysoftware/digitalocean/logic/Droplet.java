package com.redmonkeysoftware.digitalocean.logic;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Droplet implements Serializable {

    private static final long serialVersionUID = 1858736490776006069L;
    protected Long id;
    protected String name;
    protected Long memory;
    protected Long vcpus;
    protected Long disk;
    protected boolean locked;
    protected Calendar created;
    protected String status;
    protected List<Long> backupIds = new ArrayList<>();
    protected List<Long> snapshotIds = new ArrayList<>();
    protected Region region;
    protected Image image;
    protected Size size;
    protected String sizeSlug;
    protected Networks networks;
    protected Kernel kernel;
    protected BackupWindow nextBackupWindow;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMemory() {
        return memory;
    }

    public void setMemory(Long memory) {
        this.memory = memory;
    }

    public Long getVcpus() {
        return vcpus;
    }

    public void setVcpus(Long vcpus) {
        this.vcpus = vcpus;
    }

    public Long getDisk() {
        return disk;
    }

    public void setDisk(Long disk) {
        this.disk = disk;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @JsonProperty("created_at")
    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("backup_ids")
    public List<Long> getBackupIds() {
        return backupIds;
    }

    public void setBackupIds(List<Long> backupIds) {
        this.backupIds = backupIds;
    }

    @JsonProperty("snapshot_ids")
    public List<Long> getSnapshotIds() {
        return snapshotIds;
    }

    public void setSnapshotIds(List<Long> snapshotIds) {
        this.snapshotIds = snapshotIds;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @JsonProperty("size_slug")
    public String getSizeSlug() {
        return sizeSlug;
    }

    public void setSizeSlug(String sizeSlug) {
        this.sizeSlug = sizeSlug;
    }

    public Networks getNetworks() {
        return networks;
    }

    public void setNetworks(Networks networks) {
        this.networks = networks;
    }

    public Kernel getKernel() {
        return kernel;
    }

    public void setKernel(Kernel kernel) {
        this.kernel = kernel;
    }

    @JsonProperty("next_backup_window")
    public BackupWindow getNextBackupWindow() {
        return nextBackupWindow;
    }

    public void setNextBackupWindow(BackupWindow nextBackupWindow) {
        this.nextBackupWindow = nextBackupWindow;
    }
}

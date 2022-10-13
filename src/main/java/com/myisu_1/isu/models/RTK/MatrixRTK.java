package com.myisu_1.isu.models.RTK;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MatrixRTK {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String Cluster;
    private String VnutrLowVideoCam;
    private String VideoCamVnutrMiddle;
    private String VnutrHighVideoCam;
    private String VneshKupolVideoCam;
    private String VneshCylindrVideoCam;
    private String VneshHighVideoCam;
    private String VneshWiFiVideoCam;
    private String IptvBox;
    private String SmartMiniSpeaker;
    private String SmartColumnCapsule;
    private String RostelecomRouter;
    private String PowerInjectorForVideoCam;
    private String GameController;

    public MatrixRTK() {
    }

    public MatrixRTK(int id, String cluster, String vnutrLowVideoCam, String videoCamVnutrMiddle, String vnutrHighVideoCam, String vneshKupolVideoCam, String vneshCylindrVideoCam, String vneshHighVideoCam, String vneshWiFiVideoCam, String iptvBox, String smartMiniSpeaker, String smartColumnCapsule, String rostelecomRouter, String powerInjectorForVideoCam, String gameController) {
        this.id = id;
        Cluster = cluster;
        VnutrLowVideoCam = vnutrLowVideoCam;
        VideoCamVnutrMiddle = videoCamVnutrMiddle;
        VnutrHighVideoCam = vnutrHighVideoCam;
        VneshKupolVideoCam = vneshKupolVideoCam;
        VneshCylindrVideoCam = vneshCylindrVideoCam;
        VneshHighVideoCam = vneshHighVideoCam;
        VneshWiFiVideoCam = vneshWiFiVideoCam;
        IptvBox = iptvBox;
        SmartMiniSpeaker = smartMiniSpeaker;
        SmartColumnCapsule = smartColumnCapsule;
        RostelecomRouter = rostelecomRouter;
        PowerInjectorForVideoCam = powerInjectorForVideoCam;
        GameController = gameController;
    }

    public MatrixRTK(String cluster, String vnutrLowVideoCam, String videoCamVnutrMiddle, String vnutrHighVideoCam, String vneshKupolVideoCam, String vneshCylindrVideoCam, String vneshHighVideoCam, String vneshWiFiVideoCam, String iptvBox, String smartMiniSpeaker, String smartColumnCapsule, String rostelecomRouter, String powerInjectorForVideoCam, String gameController) {
        Cluster = cluster;
        VnutrLowVideoCam = vnutrLowVideoCam;
        VideoCamVnutrMiddle = videoCamVnutrMiddle;
        VnutrHighVideoCam = vnutrHighVideoCam;
        VneshKupolVideoCam = vneshKupolVideoCam;
        VneshCylindrVideoCam = vneshCylindrVideoCam;
        VneshHighVideoCam = vneshHighVideoCam;
        VneshWiFiVideoCam = vneshWiFiVideoCam;
        IptvBox = iptvBox;
        SmartMiniSpeaker = smartMiniSpeaker;
        SmartColumnCapsule = smartColumnCapsule;
        RostelecomRouter = rostelecomRouter;
        PowerInjectorForVideoCam = powerInjectorForVideoCam;
        GameController = gameController;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCluster() {
        return Cluster;
    }

    public void setCluster(String cluster) {
        Cluster = cluster;
    }

    public String getVnutrLowVideoCam() {
        return VnutrLowVideoCam;
    }

    public void setVnutrLowVideoCam(String vnutrLowVideoCam) {
        VnutrLowVideoCam = vnutrLowVideoCam;
    }

    public String getVideoCamVnutrMiddle() {
        return VideoCamVnutrMiddle;
    }

    public void setVideoCamVnutrMiddle(String videoCamVnutrMiddle) {
        VideoCamVnutrMiddle = videoCamVnutrMiddle;
    }

    public String getVnutrHighVideoCam() {
        return VnutrHighVideoCam;
    }

    public void setVnutrHighVideoCam(String vnutrHighVideoCam) {
        VnutrHighVideoCam = vnutrHighVideoCam;
    }

    public String getVneshKupolVideoCam() {
        return VneshKupolVideoCam;
    }

    public void setVneshKupolVideoCam(String vneshKupolVideoCam) {
        VneshKupolVideoCam = vneshKupolVideoCam;
    }

    public String getVneshCylindrVideoCam() {
        return VneshCylindrVideoCam;
    }

    public void setVneshCylindrVideoCam(String vneshCylindrVideoCam) {
        VneshCylindrVideoCam = vneshCylindrVideoCam;
    }

    public String getVneshHighVideoCam() {
        return VneshHighVideoCam;
    }

    public void setVneshHighVideoCam(String vneshHighVideoCam) {
        VneshHighVideoCam = vneshHighVideoCam;
    }

    public String getVneshWiFiVideoCam() {
        return VneshWiFiVideoCam;
    }

    public void setVneshWiFiVideoCam(String vneshWiFiVideoCam) {
        VneshWiFiVideoCam = vneshWiFiVideoCam;
    }

    public String getIptvBox() {
        return IptvBox;
    }

    public void setIptvBox(String iptvBox) {
        IptvBox = iptvBox;
    }

    public String getSmartMiniSpeaker() {
        return SmartMiniSpeaker;
    }

    public void setSmartMiniSpeaker(String smartMiniSpeaker) {
        SmartMiniSpeaker = smartMiniSpeaker;
    }

    public String getSmartColumnCapsule() {
        return SmartColumnCapsule;
    }

    public void setSmartColumnCapsule(String smartColumnCapsule) {
        SmartColumnCapsule = smartColumnCapsule;
    }

    public String getRostelecomRouter() {
        return RostelecomRouter;
    }

    public void setRostelecomRouter(String rostelecomRouter) {
        RostelecomRouter = rostelecomRouter;
    }

    public String getPowerInjectorForVideoCam() {
        return PowerInjectorForVideoCam;
    }

    public void setPowerInjectorForVideoCam(String powerInjectorForVideoCam) {
        PowerInjectorForVideoCam = powerInjectorForVideoCam;
    }

    public String getGameController() {
        return GameController;
    }

    public void setGameController(String gameController) {
        GameController = gameController;
    }
}

package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.MatrixRTK;
import com.myisu_1.isu.repo.MatrixRTKRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class MatrixRTKController {
    @Autowired
    private MatrixRTKRepository matrixRTKRepository;
    @GetMapping("/MatrixRTK")
    public String MatrixPhone(Model model) {
        model.addAttribute("RTK", matrixRTKRepository.findAll());
        return "MatrixRTK";
    }
    @ResponseBody
    @RequestMapping(value = "update_MatrixRTK/{id}", method = RequestMethod.GET)
    public Optional<MatrixRTK> update(@PathVariable("id") int id) {
        return matrixRTKRepository.findById(id);
    }
    @PostMapping("/add_MatrixRTK")
    public String add_phone(@RequestParam int IDupdateMatrixRTK,
                            @RequestParam String Cluster,
                            @RequestParam String VnutrLowVideoCam,
                            @RequestParam String VideoCamVnutrMiddle,
                            @RequestParam String VnutrHighVideoCam,
                            @RequestParam String VneshKupolVideoCam,
                            @RequestParam String VneshCylindrVideoCam,
                            @RequestParam String VneshHighVideoCam,
                            @RequestParam String VneshWiFiVideoCam,
                            @RequestParam String IPTVset_topBox,
                            @RequestParam String SmartMiniSpeaker,
                            @RequestParam String SmartColumnCapsule,
                            @RequestParam String RostelecomRouter,
                            @RequestParam String PowerInjectorForVideoCam,
                            @RequestParam String GameController,

                            Model model) {

        if (IDupdateMatrixRTK != 0) {
            matrixRTKRepository.save((new MatrixRTK(IDupdateMatrixRTK, Cluster, VnutrLowVideoCam, VideoCamVnutrMiddle, VnutrHighVideoCam, VneshKupolVideoCam, VneshCylindrVideoCam, VneshHighVideoCam, VneshWiFiVideoCam, IPTVset_topBox, SmartMiniSpeaker, SmartColumnCapsule, RostelecomRouter, PowerInjectorForVideoCam, GameController)));
        } else {
            matrixRTKRepository.save((new MatrixRTK(Cluster, VnutrLowVideoCam, VideoCamVnutrMiddle, VnutrHighVideoCam, VneshKupolVideoCam, VneshCylindrVideoCam, VneshHighVideoCam, VneshWiFiVideoCam, IPTVset_topBox, SmartMiniSpeaker, SmartColumnCapsule, RostelecomRouter, PowerInjectorForVideoCam, GameController)));
        }

        model.addAttribute("RTK", matrixRTKRepository.findAll());
        return "MatrixRTK";
    }
    @PostMapping("/delet_MatrixRTK")
    public String delet(@RequestParam int IDMatrixRTK, Model model) {
        matrixRTKRepository.deleteById(IDMatrixRTK);
        model.addAttribute("RTK", matrixRTKRepository.findAll());
        return "MatrixRTK";
    }
}

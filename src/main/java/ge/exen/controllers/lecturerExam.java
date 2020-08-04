package ge.exen.controllers;

import ge.exen.DAO.ExamDao;
import ge.exen.configs.GlobalConstants;
import ge.exen.models.Exam;
import ge.exen.services.FileWorkerService;
import ge.exen.services.IExamMaterial;
import ge.exen.services.RandomNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class lecturerExam {
    @Autowired
    ExamDao exam;

    @Autowired
    IExamMaterial material;

    @Autowired
    FileWorkerService fileService;


    @GetMapping("/lecturer/exam/{exam_id}")
    public String statementEditor( @PathVariable Integer exam_id, Model model){
        Exam e = exam.get(exam_id);
        model.addAttribute("varNum", e.getVariants());
        model.addAttribute("content", "changeStatements.jsp");
        model.addAttribute("title", "პირობების პანელი");
        if(GlobalConstants.DEBUG)
            System.out.println("variantebis raod.: "+e.getVariants());

        List<Boolean> uploaded = new ArrayList<>();
        for(int i = 1; i <= e.getVariants(); i ++){
            uploaded.add(material.getMaterial(i, exam_id) != null);
        }
        model.addAttribute("uploaded", uploaded);
        return "template";
    }


    @PostMapping("/lecturer/exam/{exam_id}")
    public String getFiles(MultipartHttpServletRequest req, @PathVariable Integer exam_id){
        String directory = "src/main/webapp/resources/files/statements/exam_"+exam_id+"/";
        Map<String, MultipartFile> files = req.getFileMap();
        for(Map.Entry<String, MultipartFile> entry : files.entrySet()){
            if(entry.getValue().isEmpty()) continue;
            int var = Integer.parseInt(entry.getKey().substring(10));
            String path = fileService.storeMultiPartFile(directory, entry.getValue(), RandomNameGenerator.generate(10));
            material.setMaterial(var, exam_id, path);
        }
        return "template";
    }
}

package uz.pdp.appapicodidingbat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appapicodidingbat.entity.Section;
import uz.pdp.appapicodidingbat.payload.Result;
import uz.pdp.appapicodidingbat.payload.SectionDTO;
import uz.pdp.appapicodidingbat.service.SectionService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/section")
public class SectionController {

    final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }


    @GetMapping
    public ResponseEntity<?> getSectionService() {

        List<Section> sectionService = this.sectionService.getSectionService();
        return ResponseEntity.ok(sectionService);
    }

    @GetMapping(value = "/{sectionId}")
    public ResponseEntity<?> getSection(@PathVariable Integer sectionId) {

        Section section = sectionService.getSection(sectionId);
        return ResponseEntity.ok(section);
    }

    @PostMapping()
    public ResponseEntity<?> addSection(@Valid @RequestBody SectionDTO sectionDTO) {

        Result result = sectionService.addSection(sectionDTO);
        return ResponseEntity.status(result.getStatus() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(result);
    }

    @PutMapping(value = "/{sectionId}")
    public ResponseEntity<?> editSection(@PathVariable Integer sectionId, @Valid SectionDTO sectionDTO){

        Result result = sectionService.editSection(sectionId, sectionDTO);
        return ResponseEntity.status(result.getStatus()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(result);
    }
}

package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Notice;
import com.example.demo.repository.NoticeRepository;
import com.example.demo.service.NoticeService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
@RequiredArgsConstructor
@Controller
public class NoticeController {

	@Autowired
	private final NoticeService noticeService;
	private final NoticeRepository noticeRepository;
//	private final MemberService memberService;

	@GetMapping("/auth/notice/list")
	public ResponseEntity<List<Notice>> list(@RequestParam(required = false) String ntitle) {
		try {
			List<Notice> notice = new ArrayList<Notice>();
			if (ntitle == null)
				noticeRepository.findAll().forEach(notice::add);
			else
				noticeRepository.findByNtitleContaining(ntitle).forEach(notice::add);
			if (notice.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(notice, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/auth/notice/detail_admin/{nid}")
	public ResponseEntity<Notice> noticeDetailAdmin(Model model, @PathVariable("nid") Integer nid) {
		Notice notice = this.noticeService.getNotice(nid);
		model.addAttribute("notice", notice);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping("/auth/notice/detail/{nid}")
	public ResponseEntity<Notice> noticeDetail(@PathVariable("nid") Integer nid) {
		Optional<Notice> noticeData = noticeRepository.findById(nid);
		noticeData.get().setNhit(noticeData.get().getNhit() + 1);
		noticeRepository.save(noticeData.get());
		if (noticeData.isPresent()) {
			return new ResponseEntity<>(noticeData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

//	@GetMapping("/register")
//	public String registerNotice() {
//		return "noticeadd_admin";
//	}

	@PostMapping("/auth/notice/register")
	public ResponseEntity<Notice> registerNotice(@RequestBody Notice notice) {

		try {

			Notice _notice = this.noticeRepository.save(
					new Notice(notice.getNtitle(), notice.getNcontent(), notice.getNfile(), LocalDate.now(), null, 0));
			return new ResponseEntity<>(_notice, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/auth/notice/modify/{nid}")
	public ResponseEntity<Notice> noticeModify(@PathVariable("nid") Integer nid
			) {
		try {
		Optional<Notice> notice = this.noticeRepository.findById(nid); 
		notice.get().setNtitle(notice.get().getNtitle());
		notice.get().setNcontent(notice.get().getNcontent());
		notice.get().setNrdate(notice.get().getNrdate());
		notice.get().setNmodifydate(notice.get().getNmodifydate());
		notice.get().setNhit(notice.get().getNhit());
		return new ResponseEntity<>(notice.get(), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/auth/notice/modify/{nid}")
	public ResponseEntity<Notice> noticeModify(@PathVariable("nid") Integer nid, @RequestBody Notice notice) {
		
			Optional<Notice> noticeData = noticeRepository.findById(nid);
			if(noticeData.isPresent()) {
				Notice newNotice = noticeData.get();
				newNotice.setNtitle(notice.getNtitle());
				newNotice.setNcontent(notice.getNcontent());
				newNotice.setNrdate(noticeData.get().getNrdate());
				newNotice.setNmodifydate(LocalDate.now());
				newNotice.setNfile(notice.getNfile());
				newNotice.setNhit(noticeData.get().getNhit());
				return new ResponseEntity<>(noticeRepository.save(newNotice), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		
	}

	@DeleteMapping("/auth/notice/delete/{nid}")
	public ResponseEntity<HttpStatus> noticeDelete(@PathVariable("nid") Integer nid) {
		try {
			noticeRepository.deleteById(nid);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

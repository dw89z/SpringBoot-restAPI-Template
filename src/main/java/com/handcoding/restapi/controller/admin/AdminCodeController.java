package com.handcoding.restapi.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.handcoding.restapi.annotation.Access;
import com.handcoding.restapi.component.ServiceComponent;
import com.handcoding.restapi.domain.CodeVO;
import com.handcoding.restapi.domain.ResponseVO;
import com.handcoding.restapi.domain.SearchVO;
import com.handcoding.restapi.domain.in.InCodeUpdateVO;

import io.swagger.annotations.ApiOperation;

/**
 * 코드 리소스 API
 * @author 이승환
 * @version 2018.01.17 v1.1
 */
@RestController
@RequestMapping("/admin")
public class AdminCodeController {
	
	@Autowired
	private ServiceComponent service;
	
	/**
	 * 코드조회
	 * @param searchVO
	 * @return
	 */
	@ApiOperation(value="", notes = "코드조회")
	@Access(scope="private")
	@GetMapping("/v1.0/codes")
	public ResponseVO<List<CodeVO>> codeList(SearchVO searchVO) {
		ResponseVO<List<CodeVO>> responseVO = new ResponseVO<>();
		List<CodeVO> codeList = service.getCodeService().codeList(searchVO);
		responseVO.setResponse(codeList);
		if(codeList.size() == 0) {
			responseVO.setCheck(false);
		}
		return responseVO;
	}
	
	/**
	 * 코드생성
	 * @param codeVO
	 * @return
	 */
	@ApiOperation(value="", notes = "코드생성")
	@Access(scope="private")
	@PostMapping("/v1.0/codes")
	public ResponseVO<Object> codeInsert(@RequestBody CodeVO codeVO) {
		ResponseVO<Object> responseVO = new ResponseVO<>();
		service.getCodeService().codeInsert(codeVO);
		return responseVO;
	}
	
	/**
	 * 코드수정
	 * @param codeVO
	 * @param code
	 * @return
	 */
	@ApiOperation(value="", notes = "코드수정")
	@Access(scope="private")
	@PutMapping("/v1.0/codes/{code}")
	public ResponseVO<Object> codeUpdate(@RequestBody InCodeUpdateVO inCodeUpdateVO, @PathVariable String code) {
		ResponseVO<Object> responseVO = new ResponseVO<>();
		inCodeUpdateVO.setCode(code);
		boolean check = service.getCodeService().codeUpdate(inCodeUpdateVO);
		responseVO.setCheck(check);
		return responseVO;
	}
	
	/**
	 * 코드삭제
	 * @param codeVO
	 * @return
	 */
	@ApiOperation(value="", notes = "코드삭제")
	@Access(scope="private")
	@DeleteMapping("/v1.0/codes/{code}")
	public ResponseVO<Object> codeDelete(CodeVO codeVO) {
		ResponseVO<Object> responseVO = new ResponseVO<>();
		boolean check = service.getCodeService().codeDelete(codeVO);
		responseVO.setCheck(check);
		return responseVO;
	}
	
}

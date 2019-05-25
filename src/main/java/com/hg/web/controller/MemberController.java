package com.hg.web.controller;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hg.pojo.Member;
import com.hg.pojo.Member.AddMemberGroupSequence;
import com.hg.pojo.Member.EditMemberGroupSequence;
import com.hg.service.MemberService;
import com.hg.web.util.WebUtil;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	//导航到会员管理页面
	@RequestMapping(value="memberList", method=RequestMethod.GET)
	public String memberList(@RequestParam(required=false,defaultValue="0") Integer pageIndex,
							@RequestParam(required=false,defaultValue="3")Integer pageSize,
							Member member,Model model) throws Exception {
		model.addAttribute("pagination", memberService.getMemberList(member.getName(), member.getPhone(), pageIndex, pageSize));
		return "memberList";
	}
	@RequestMapping(value="addMember", method=RequestMethod.GET)
	public String addMember(Member member) throws Exception {
		return "addMember";
	}
	
	//添加会员
	@RequestMapping(value="addMember", method=RequestMethod.POST)
	public String addMember(@Validated(AddMemberGroupSequence.class) Member member,Errors errors) throws Exception {
		if(errors.hasErrors()){
			return "addMember";
		}
		if(memberService.isExistsByIdentityCard(null, member.getIdentityCard())){
			errors.rejectValue("identityCard","member.identityCard.exists","身份证号已经存在，请更换");
		}

		if(memberService.isExistsByPhone(null, member.getIdentityCard())){
			errors.rejectValue("phone","member.phone.exists","身份证号已经存在，请更换");
		}
		
		if(errors.hasErrors()){
			return "addMember";
		}
		boolean flag = memberService.addMember(member);
		if(flag){
			WebUtil.setSession("msg", "添加会员成功！");
			return "redirect:/member/memberList";
		}else{
			WebUtil.setRequest("msg", "添加会员失败！");
			return "addMember";
		}
	}
	//ajax验证
	@RequestMapping(value="checkExistsByIdentityCard", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Boolean> checkExistsByIdentityCard(Integer id,String identityCard)throws Exception{
		boolean flag = memberService.isExistsByIdentityCard(id, identityCard);
		Map<String,Boolean> map = new HashMap<>();
		map.put("valid", !flag);
		return map;
	}
	
	@RequestMapping(value="checkExistsByPhone",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Boolean> checkExistsByPhone(Integer id,String phone)throws Exception{
		boolean flag = memberService.isExistsByPhone(id, phone);
		Map<String,Boolean> map = new HashMap<>();
		map.put("valid", !flag);
		return map;
	}
	
	//导航到修改页面
	@RequestMapping(value="updateMember", method=RequestMethod.GET)
	public String updateMember(Member member) throws Exception {
		Member currMember = memberService.getMemberById(member.getId());
		BeanUtils.copyProperties(member, currMember);
		return "updateMember";
	}
	//修改
	@RequestMapping(value="updateMember", method=RequestMethod.POST)
	public String updateMember(@Validated(EditMemberGroupSequence.class) Member member,Errors errors) throws Exception {
		if(errors.hasErrors()){
			return "updateMember";
		}
		if(memberService.isExistsByIdentityCard(member.getId(), member.getIdentityCard())){
			errors.rejectValue("identityCard","member.identityCard.exists","身份证号已经存在，请更换");
		}
		if(memberService.isExistsByPhone(member.getId(), member.getPhone())){
			errors.rejectValue("phone", "member.phone.exists", "电话号码已经存在，请更换");
		}
		if(errors.hasErrors()){
			return "updateMember";
		}
		
		boolean flag = memberService.updateMember(member);
		if(flag){
			WebUtil.setSession("msg", "修改会员成功！");
			return "redirect:/member/memberList";
		}else{
			WebUtil.setRequest("msg", "修改会员失败！");
			return "updateMember";
		}
	}
	
	@RequestMapping(value="deleteMember", method=RequestMethod.GET)
	public String deleteMember(Member member,Errors errors) throws Exception {
		boolean flag = memberService.deleteMember(member.getId());
		if(flag){
			WebUtil.setSession("msg", "删除会员成功！");
		}else{
			WebUtil.setSession("msg", "该会员有尚未归还的图书不能删除！");
		}
		return "redirect:/member/memberList";
	}
	
}

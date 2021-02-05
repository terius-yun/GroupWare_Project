function update(){
	if(updateForm.cal_title.value.length==0){
		alert("프로젝트 이름을 입력하세요. ");
		updateForm.cal_title.focus();
		return false;
	}
	if(updateForm.cal_member.value.length==0){
		alert("참가자를 입력하세요. ");
		updateForm.cal_member.focus();
		return false;
	}
	if(updateForm.cal_content.value.length==0){
		alert("내용을 입력하세요. ");
		updateForm.cal_content.focus();
		return false;
	}
	if(updateForm.cal_start_date.value.length==0){
		alert("시작일을 입력하세요. ");
		updateForm.cal_start_date.focus();
		return false;
	}
	if(updateForm.cal_end_date.value.length==0){
		alert("종료일을 입력하세요. ");
		updateForm.cal_end_date.focus();
		return false;
	}
	
}
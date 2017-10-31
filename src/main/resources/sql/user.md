sample
===
* 注释

	select #use("cols")# from user where #use("condition")#

cols
===

	id,name,age,user_name,create_date

updateSample
===

	`id`=#id#,`name`=#name#,`age`=#age#,`user_name`=#userName#,`create_date`=#createDate#

condition
===

	1 = 1  
	@if(!isEmpty(name)){
	 and `name`=#name#
	@}
	@if(!isEmpty(age)){
	 and `age`=#age#
	@}
	@if(!isEmpty(userName)){
	 and `user_name`=#userName#
	@}
	@if(!isEmpty(createDate)){
	 and `create_date`=#createDate#
	@}
	

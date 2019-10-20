sample
===
* 注释


	select #use("cols")# from user  where  #use("condition")#

selectUserAndRole
===
* 查询用户及角色信息

    
    select #use("cols")# from user where id=#userId#

selectRole
===

    select r.* from user_role ur left join role r on ur.role_id=r.id
    
    where ur.user_id=#userId#
    
pageQueryByCondition
===

    select 
    @pageTag(){
        #use("cols")#
    @} 
    from user  where  #use("condition")#

cols
===
	id,create_time,last_modified_time,active,avatar,email,_group,notify_count,openid,password,permission,phone_number,role,signature,user_name,create_by_id,last_modified_by_id

updateSample
===
	
	id=#id#,create_time=#createTime#,last_modified_time=#lastModifiedTime#,active=#active#,avatar=#avatar#,email=#email#,_group=#Group#,notify_count=#notifyCount#,openid=#openid#,password=#password#,permission=#permission#,phone_number=#phoneNumber#,role=#role#,signature=#signature#,user_name=#userName#,create_by_id=#createById#,last_modified_by_id=#lastModifiedById#

getRoleByUid
===

    select role_name from role left join user_role on role_id = id 
    
    where user_id = #uid#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(createTime)){
	 and create_time=#createTime#
	@}
	@if(!isEmpty(lastModifiedTime)){
	 and last_modified_time=#lastModifiedTime#
	@}
	@if(!isEmpty(active)){
	 and active=#active#
	@}
	@if(!isEmpty(avatar)){
	 and avatar=#avatar#
	@}
	@if(!isEmpty(email)){
	 and email=#email#
	@}
	@if(!isEmpty(Group)){
	 and _group=#Group#
	@}
	@if(!isEmpty(notifyCount)){
	 and notify_count=#notifyCount#
	@}
	@if(!isEmpty(openid)){
	 and openid=#openid#
	@}
	@if(!isEmpty(password)){
	 and password=#password#
	@}
	@if(!isEmpty(permission)){
	 and permission=#permission#
	@}
	@if(!isEmpty(phoneNumber)){
	 and phone_number=#phoneNumber#
	@}
	@if(!isEmpty(role)){
	 and role=#role#
	@}
	@if(!isEmpty(signature)){
	 and signature=#signature#
	@}
	@if(!isEmpty(userName)){
	 and user_name=#userName#
	@}
	@if(!isEmpty(createById)){
	 and create_by_id=#createById#
	@}
	@if(!isEmpty(lastModifiedById)){
	 and last_modified_by_id=#lastModifiedById#
	@}

sample
===
* 注释

	select #use("cols")# from permission  where  #use("condition")#

cols
===
	id,create_time,last_modified_time,permission_name,create_by_id,last_modified_by_id,create_by,last_modified_by

updateSample
===
	
	id=#id#,create_time=#createTime#,last_modified_time=#lastModifiedTime#,permission_name=#permissionName#,create_by_id=#createById#,last_modified_by_id=#lastModifiedById#,create_by=#createBy#,last_modified_by=#lastModifiedBy#

getPermissionByRoleId
===
   
    select #use("cols")# from permission left join role_permission on permission_id = id   
    where role_id =#roleId#

getPermissionByRoleIds
===

    select #use("cols")# from permission left join role_permission on permission_id = id   
    where role_id in(
    @for(id in ids){
    #id#  #text(idLP.last?"":"," )#
    @}
    )

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
	@if(!isEmpty(permissionName)){
	 and permission_name=#permissionName#
	@}
	@if(!isEmpty(createById)){
	 and create_by_id=#createById#
	@}
	@if(!isEmpty(lastModifiedById)){
	 and last_modified_by_id=#lastModifiedById#
	@}
	@if(!isEmpty(createBy)){
	 and create_by=#createBy#
	@}
	@if(!isEmpty(lastModifiedBy)){
	 and last_modified_by=#lastModifiedBy#
	@}
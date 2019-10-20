sample
===
* 注释


	select #use("cols")# from role  where  #use("condition")#
		
selectPermission
===

    select p.* from role_permission rp left join permission p
        on rp.permission_id = p.id
        where rp.role_id =#roleId#

cols
===
	id,create_time,last_modified_time,role_name,create_by_id,last_modified_by_id

updateSample
===
	
	id=#id#,create_time=#createTime#,last_modified_time=#lastModifiedTime#,role_name=#roleName#,create_by_id=#createById#,last_modified_by_id=#lastModifiedById#

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
	@if(!isEmpty(roleName)){
	 and role_name=#roleName#
	@}
	@if(!isEmpty(createById)){
	 and create_by_id=#createById#
	@}
	@if(!isEmpty(lastModifiedById)){
	 and last_modified_by_id=#lastModifiedById#
	@}

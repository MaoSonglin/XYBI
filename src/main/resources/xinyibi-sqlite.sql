create table relationship(
	id integer primary key,
	category varchar(255), --关联类型
	ref_id	varchar(255), -- 参考对象的ID
	obj_id	varchar(255)-- 原对象的ID
)
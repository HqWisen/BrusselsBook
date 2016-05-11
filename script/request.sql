# R1 (sure)

select user.UID from BookUser user where 
(
select count(*) from BookUser ub, Establishment e where
ub.Username = 'Brenda'
and
exists(select * from BookComment c where c.EID = e.EID and c.UID = ub.UID and c.Score > 4)
and
exists(select * from BookComment c where c.EID = e.EID and c.UID = user.UID and c.Score > 4)
) > 3;

# R5 (not sure)

select c.EID, AVG(c.Score) as AvgScore from BookComment c
where c.EID in
(
select eid as eid3 from Establishment e where
(
select count(*) from BookComment c where eid3 = c.EID
) > 3
)
group by (c.EID) order by AvgScore;

# R3 (not sure)

select e.EID from Establishment e where
(
select count(*) from BookComment c where c.EID = e.EID
) <= 1;
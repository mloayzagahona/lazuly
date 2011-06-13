-- table : country
-- table : role
-- table : address
-- table : conference
-- table : conference_feedback
-- table : conference_member
ALTER TABLE conference_member ADD CONSTRAINT $property.name CHECK (status IN 
 (
  'PENDING',
  'ACTIVE'
 ) ENABLE
);
-- table : evaluation
-- table : member_role
-- table : presentation
ALTER TABLE presentation ADD CONSTRAINT $property.name CHECK (status IN 
 (
  'PROPOSAL',
  'ACTIVE'
 ) ENABLE
);
-- table : presentation_place
-- table : speaker
-- table : speaker_presentation
-- table : sponsor
ALTER TABLE sponsor ADD CONSTRAINT $property.name CHECK (privilege_type IN 
 (
  'Golden',
  'Silver',
  'Bronze'
 ) ENABLE
);
ALTER TABLE sponsor ADD CONSTRAINT $property.name CHECK (status IN 
 (
  'PENDING',
  'ACTIVE'
 ) ENABLE
);

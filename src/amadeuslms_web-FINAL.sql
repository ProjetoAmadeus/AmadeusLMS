<<<<<<< HEAD
=======
 
>>>>>>> 661708b07f533da1f47ab2b8c362cb287fdf4631
--
-- PostgreSQL database dump
--

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

<<<<<<< HEAD

=======
>>>>>>> 661708b07f533da1f47ab2b8c362cb287fdf4631
--
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: postgres
--

CREATE PROCEDURAL LANGUAGE plpgsql;


ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO postgres;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: accessinfo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE accessinfo (
    id integer NOT NULL,
    login character varying(255),
    password character varying(255),
    profile_type integer NOT NULL
);


ALTER TABLE public.accessinfo OWNER TO postgres;

--
-- Name: alternative; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE alternative (
    id integer NOT NULL,
    correct boolean NOT NULL,
    description character varying(8000),
    question_id integer
);


ALTER TABLE public.alternative OWNER TO postgres;

--
-- Name: alternativerealized; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE alternativerealized (
    id integer NOT NULL,
    answer boolean NOT NULL,
    alternative_id integer,
    questionrealized_id integer NOT NULL
);


ALTER TABLE public.alternativerealized OWNER TO postgres;

--
<<<<<<< HEAD
-- Name: amadeus_droid_historic; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE amadeus_droid_historic (
    id integer NOT NULL,
    action_value character varying(255),
    course_name character varying(255),
    creation_date timestamp without time zone,
    resourse_name character varying(255),
    user_name character varying(255)
);


ALTER TABLE public.amadeus_droid_historic OWNER TO postgres;

--
=======
>>>>>>> 661708b07f533da1f47ab2b8c362cb287fdf4631
-- Name: answer; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE answer (
    id integer NOT NULL,
    answerdate timestamp without time zone,
    person_id integer,
    poll_id integer NOT NULL,
    "position" integer
);


ALTER TABLE public.answer OWNER TO postgres;

--
-- Name: archive; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE archive (
    id integer NOT NULL,
    archive bytea
);


ALTER TABLE public.archive OWNER TO postgres;

--
-- Name: association; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE association (
    questionassociation_id integer NOT NULL,
    value character varying(255) NOT NULL,
    _key character varying(255) NOT NULL
);


ALTER TABLE public.association OWNER TO postgres;

--
-- Name: associationrealized; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE associationrealized (
    questionrealized_id integer NOT NULL,
    value character varying(255) NOT NULL,
    _key character varying(255) NOT NULL
);


ALTER TABLE public.associationrealized OWNER TO postgres;

--
-- Name: choice; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE choice (
    id integer NOT NULL,
    alternative character varying(255),
    percentage double precision NOT NULL,
    votes integer NOT NULL,
    poll_id integer NOT NULL,
    "position" integer
);


ALTER TABLE public.choice OWNER TO postgres;

--
-- Name: commentary; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE commentary (
    id integer NOT NULL,
    description character varying(255)
);


ALTER TABLE public.commentary OWNER TO postgres;

--
-- Name: course; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE course (
    id integer NOT NULL,
    content character varying(2000),
    creationdate timestamp without time zone,
    finalcoursedate timestamp without time zone,
    finalregistrationdate timestamp without time zone,
    initialcoursedate timestamp without time zone,
    initialregistrationdate timestamp without time zone,
    maxamountstudents integer NOT NULL,
    name character varying(255),
    objectives character varying(2000),
    professor_id integer
);


ALTER TABLE public.course OWNER TO postgres;

--
-- Name: course_coursescores; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE course_coursescores (
    course_id integer NOT NULL,
    studentsscores_id integer NOT NULL
);


ALTER TABLE public.course_coursescores OWNER TO postgres;

--
-- Name: courseevaluation; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE courseevaluation (
    id integer NOT NULL,
    initdate timestamp without time zone
);


ALTER TABLE public.courseevaluation OWNER TO postgres;

--
-- Name: courseevaluation_commentary; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE courseevaluation_commentary (
    courseevaluation_id integer NOT NULL,
    commentaries_id integer NOT NULL,
    commentary_position integer NOT NULL
);


ALTER TABLE public.courseevaluation_commentary OWNER TO postgres;

--
-- Name: courseevaluation_criterion; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE courseevaluation_criterion (
    courseevaluation_id integer NOT NULL,
    criteria_id integer NOT NULL,
    criterion_position integer NOT NULL
);


ALTER TABLE public.courseevaluation_criterion OWNER TO postgres;

--
-- Name: courseevaluation_person; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE courseevaluation_person (
    courseevaluation_id integer NOT NULL,
    students_id integer NOT NULL
);


ALTER TABLE public.courseevaluation_person OWNER TO postgres;

--
-- Name: coursescores; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE coursescores (
    id integer NOT NULL,
    coursescore double precision NOT NULL,
    student_id integer,
    module_id integer NOT NULL
);


ALTER TABLE public.coursescores OWNER TO postgres;

--
-- Name: criterion; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE criterion (
    id integer NOT NULL,
    ctype integer
);


ALTER TABLE public.criterion OWNER TO postgres;

--
-- Name: criterion_criterionanswers; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE criterion_criterionanswers (
    criterion_id integer NOT NULL,
    answers_id integer NOT NULL
);


ALTER TABLE public.criterion_criterionanswers OWNER TO postgres;

--
-- Name: criterionanswers; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE criterionanswers (
    id integer NOT NULL,
    aalmostalwaysvotes integer NOT NULL,
    aalmostnevervotes integer NOT NULL,
    afrequentlyvotes integer NOT NULL,
    ararelyvotes integer NOT NULL,
    asometimesvotes integer NOT NULL,
    atotalvotes integer NOT NULL,
    dalmostalwaysvotes integer NOT NULL,
    dalmostnevervotes integer NOT NULL,
    dfrequentlyvotes integer NOT NULL,
    drarelyvotes integer NOT NULL,
    dsometimesvotes integer NOT NULL,
    dtotalvotes integer NOT NULL
);


ALTER TABLE public.criterionanswers OWNER TO postgres;

--
-- Name: delivery; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE delivery (
    id integer NOT NULL,
    archive bytea,
    date timestamp without time zone,
    person_id integer,
    homework_id integer
);


ALTER TABLE public.delivery OWNER TO postgres;

--
-- Name: evaluation; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE evaluation (
    id integer NOT NULL,
    afterdeadlineachieved boolean NOT NULL,
    description character varying(8000),
    finish timestamp without time zone,
    start timestamp without time zone,
    module_id integer
);


ALTER TABLE public.evaluation OWNER TO postgres;

--
-- Name: evaluation_question; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE evaluation_question (
    evaluation_id integer NOT NULL,
    question_id integer NOT NULL
);


ALTER TABLE public.evaluation_question OWNER TO postgres;

--
-- Name: evaluationrealized; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE evaluationrealized (
    id integer NOT NULL,
    correcteddate timestamp without time zone,
    grade real NOT NULL,
    realizeddate timestamp without time zone,
    person_id integer,
    evaluation_id integer NOT NULL
);


ALTER TABLE public.evaluationrealized OWNER TO postgres;

--
-- Name: externallink; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE externallink (
    id integer NOT NULL,
    creationdate date,
    description character varying(1000),
    name character varying(255),
    url character varying(500),
    module_id integer NOT NULL
);


ALTER TABLE public.externallink OWNER TO postgres;

--
-- Name: forum; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE forum (
    id integer NOT NULL,
<<<<<<< HEAD
    description character varying(9000),
=======
    description character varying(255),
>>>>>>> 661708b07f533da1f47ab2b8c362cb287fdf4631
    creationdate timestamp without time zone,
    name character varying(255),
    module_id integer NOT NULL
);


ALTER TABLE public.forum OWNER TO postgres;

--
-- Name: game; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE game (
    id integer NOT NULL,
    creationdate timestamp without time zone,
    description character varying(255),
    image bytea,
    maxusers integer NOT NULL,
    minusers integer NOT NULL,
    name character varying(255),
    url character varying(255),
<<<<<<< HEAD
    module_id integer NOT NULL,
    linkexterno boolean DEFAULT false
);


=======
    module_id integer NOT NULL
);



>>>>>>> 661708b07f533da1f47ab2b8c362cb287fdf4631
ALTER TABLE public.game OWNER TO postgres;

--
-- Name: gapanswer; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE gapanswer (
    questionrealized_id integer NOT NULL,
    answer character varying(255) NOT NULL
);


ALTER TABLE public.gapanswer OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: historylearningobject; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE historylearningobject (
    id integer NOT NULL,
    dateaccess timestamp without time zone,
    dateendaccess timestamp without time zone,
    score character varying(255),
    timeaccess time without time zone,
    learning_object_id integer NOT NULL,
    person_id integer NOT NULL
);


ALTER TABLE public.historylearningobject OWNER TO postgres;

--
-- Name: homework; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE homework (
    id integer NOT NULL,
    allowpostponing timestamp without time zone,
    deadline timestamp without time zone,
    description character varying(255),
    initdate timestamp without time zone,
    name character varying(255),
    module_id integer NOT NULL
);


ALTER TABLE public.homework OWNER TO postgres;

--
-- Name: image; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE image (
    id integer NOT NULL,
    photo bytea
);


ALTER TABLE public.image OWNER TO postgres;

--
-- Name: keyword; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE keyword (
    id integer NOT NULL,
    name character varying(255),
    popularity integer NOT NULL
);


ALTER TABLE public.keyword OWNER TO postgres;

--
-- Name: keywordsofcourse; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE keywordsofcourse (
    course_id integer NOT NULL,
    keywords_id integer NOT NULL
);


ALTER TABLE public.keywordsofcourse OWNER TO postgres;

--
-- Name: learningobject; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE learningobject (
    id integer NOT NULL,
    creationdate timestamp without time zone,
    description character varying(255),
    name character varying(255),
    url character varying(255),
    module_id integer NOT NULL
);


ALTER TABLE public.learningobject OWNER TO postgres;

--
<<<<<<< HEAD
-- Name: log; Type: TABLE; Schema: public; Owner: amadeus; Tablespace: 
--

CREATE TABLE log (
    id integer NOT NULL,
    date timestamp without time zone,
    person_id integer,
    codigo integer,
    idobjeto integer,
    fases integer,
    tempo integer,
    pontuacao integer,
    metaalternativa integer,
    tamanhomensagem integer
);


ALTER TABLE public.log OWNER TO postgres;

--
=======
>>>>>>> 661708b07f533da1f47ab2b8c362cb287fdf4631
-- Name: material; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE material (
    id integer NOT NULL,
    allowlatedeliveries boolean NOT NULL,
    archivename character varying(255),
    correcteddate timestamp without time zone,
    extension character varying(255),
    grade real,
    creationdate timestamp without time zone,
    author_id integer,
    archive_id integer NOT NULL,
    module_id integer NOT NULL,
    request_id integer
);


ALTER TABLE public.material OWNER TO postgres;

--
-- Name: materialrequest; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE materialrequest (
    id integer NOT NULL,
    allowlatedeliveries boolean NOT NULL,
    deliverydate timestamp without time zone,
    description character varying(255),
    name character varying(255),
    module_id integer NOT NULL,
    creationdate timestamp without time zone
);


ALTER TABLE public.materialrequest OWNER TO postgres;

--
-- Name: message; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE message (
    id integer NOT NULL,
<<<<<<< HEAD
    body character varying(9000),
=======
    body character varying(2000),
>>>>>>> 661708b07f533da1f47ab2b8c362cb287fdf4631
    date timestamp without time zone,
    forum_id integer NOT NULL,
    author_id integer,
    "position" integer
);


ALTER TABLE public.message OWNER TO postgres;

--
-- Name: module; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE module (
    id integer NOT NULL,
    description character varying(1500),
    name character varying(255),
    "position" integer NOT NULL,
    visible boolean NOT NULL,
    course_id integer NOT NULL
);


ALTER TABLE public.module OWNER TO postgres;

--
-- Name: openid; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE openid (
    id integer NOT NULL,
    email character varying(255),
    _identity character varying(255),
    name character varying(255),
    provideby character varying(255),
    accessinfo_id integer NOT NULL
);


ALTER TABLE public.openid OWNER TO postgres;

--
-- Name: person; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE person (
    id integer NOT NULL,
    birthdate timestamp without time zone,
    city character varying(255),
    cpf character varying(255),
    email character varying(255),
    gender character(1) NOT NULL,
    name character varying(255),
    phonenumber character varying(255),
    state character varying(255),
    access_info_id integer,
    image_id integer,
<<<<<<< HEAD
    resume_id integer,
	twitterlogin character varying(255),
	facebooklogin character varying(255)
=======
    resume_id integer
>>>>>>> 661708b07f533da1f47ab2b8c362cb287fdf4631
);


ALTER TABLE public.person OWNER TO postgres;

--
-- Name: person_role_course; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE person_role_course (
    id integer NOT NULL,
    person_id integer,
    course_id integer NOT NULL,
    role_id integer
);


ALTER TABLE public.person_role_course OWNER TO postgres;

--
-- Name: poll; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE poll (
    id integer NOT NULL,
    finishdate timestamp without time zone,
    creationdate timestamp without time zone,
    name character varying(255),
    question character varying(255),
    module_id integer NOT NULL
);


ALTER TABLE public.poll OWNER TO postgres;

--
-- Name: question; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE question (
    dtype character varying(31) NOT NULL,
    id integer NOT NULL,
    description character varying(8000),
    course_id integer NOT NULL
);


ALTER TABLE public.question OWNER TO postgres;

--
-- Name: questionrealized; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE questionrealized (
    dtype character varying(31) NOT NULL,
    id integer NOT NULL,
    comment character varying(8000),
    grade real NOT NULL,
    answer character varying(8000),
    question_id integer,
    evaluationrealized_id integer NOT NULL
);


ALTER TABLE public.questionrealized OWNER TO postgres;

--
-- Name: resume; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE resume (
    id integer NOT NULL,
    degree character varying(255),
    description character varying(255),
    instituition character varying(255),
    year integer
);


ALTER TABLE public.resume OWNER TO postgres;

--
-- Name: role; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE role (
    id integer NOT NULL,
    role_type integer NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- Name: userrequest; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE userrequest (
    id integer NOT NULL,
    interest character varying(255),
    status_type integer NOT NULL,
    teachingrequest boolean NOT NULL,
    userrequestdate timestamp without time zone,
    course_id integer,
    person_id integer
);


ALTER TABLE public.userrequest OWNER TO postgres;

--
-- Name: videoiriz; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE videoiriz (
    id integer NOT NULL,
    description character varying(255),
    name character varying(255),
    uploaded boolean NOT NULL,
    youtubeid character varying(255),
    module_id integer NOT NULL,
    creationdate timestamp without time zone
);


ALTER TABLE public.videoiriz OWNER TO postgres;

--
-- Name: accessinfo_login_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY accessinfo
    ADD CONSTRAINT accessinfo_login_key UNIQUE (login);


--
-- Name: accessinfo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY accessinfo
    ADD CONSTRAINT accessinfo_pkey PRIMARY KEY (id);


--
-- Name: alternative_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY alternative
    ADD CONSTRAINT alternative_pkey PRIMARY KEY (id);


--
-- Name: alternativerealized_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY alternativerealized
    ADD CONSTRAINT alternativerealized_pkey PRIMARY KEY (id);


--
<<<<<<< HEAD
-- Name: amadeus_droid_historic_pkey; Type: CONSTRAINT; Schema: public; Owner: amadeus; Tablespace: 
--

ALTER TABLE ONLY amadeus_droid_historic
    ADD CONSTRAINT amadeus_droid_historic_pkey PRIMARY KEY (id);


--
=======
>>>>>>> 661708b07f533da1f47ab2b8c362cb287fdf4631
-- Name: answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY answer
    ADD CONSTRAINT answer_pkey PRIMARY KEY (id);


--
-- Name: archive_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY archive
    ADD CONSTRAINT archive_pkey PRIMARY KEY (id);


--
-- Name: association_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY association
    ADD CONSTRAINT association_pkey PRIMARY KEY (questionassociation_id, _key);


--
-- Name: associationrealized_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY associationrealized
    ADD CONSTRAINT associationrealized_pkey PRIMARY KEY (questionrealized_id, _key);


--
-- Name: choice_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY choice
    ADD CONSTRAINT choice_pkey PRIMARY KEY (id);


--
-- Name: commentary_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY commentary
    ADD CONSTRAINT commentary_pkey PRIMARY KEY (id);


--
-- Name: course_coursescores_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY course_coursescores
    ADD CONSTRAINT course_coursescores_pkey PRIMARY KEY (course_id, studentsscores_id);


--
-- Name: course_coursescores_studentsscores_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY course_coursescores
    ADD CONSTRAINT course_coursescores_studentsscores_id_key UNIQUE (studentsscores_id);


--
-- Name: course_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY course
    ADD CONSTRAINT course_pkey PRIMARY KEY (id);


--
-- Name: courseevaluation_commentary_commentaries_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY courseevaluation_commentary
    ADD CONSTRAINT courseevaluation_commentary_commentaries_id_key UNIQUE (commentaries_id);


--
-- Name: courseevaluation_commentary_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY courseevaluation_commentary
    ADD CONSTRAINT courseevaluation_commentary_pkey PRIMARY KEY (courseevaluation_id, commentary_position);


--
-- Name: courseevaluation_criterion_criteria_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY courseevaluation_criterion
    ADD CONSTRAINT courseevaluation_criterion_criteria_id_key UNIQUE (criteria_id);


--
-- Name: courseevaluation_criterion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY courseevaluation_criterion
    ADD CONSTRAINT courseevaluation_criterion_pkey PRIMARY KEY (courseevaluation_id, criterion_position);


--
-- Name: courseevaluation_person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY courseevaluation_person
    ADD CONSTRAINT courseevaluation_person_pkey PRIMARY KEY (courseevaluation_id, students_id);


--
-- Name: courseevaluation_person_students_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY courseevaluation_person
    ADD CONSTRAINT courseevaluation_person_students_id_key UNIQUE (students_id);


--
-- Name: courseevaluation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY courseevaluation
    ADD CONSTRAINT courseevaluation_pkey PRIMARY KEY (id);


--
-- Name: coursescores_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY coursescores
    ADD CONSTRAINT coursescores_pkey PRIMARY KEY (id);


--
-- Name: criterion_criterionanswers_answers_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY criterion_criterionanswers
    ADD CONSTRAINT criterion_criterionanswers_answers_id_key UNIQUE (answers_id);


--
-- Name: criterion_criterionanswers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY criterion_criterionanswers
    ADD CONSTRAINT criterion_criterionanswers_pkey PRIMARY KEY (criterion_id, answers_id);


--
-- Name: criterion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY criterion
    ADD CONSTRAINT criterion_pkey PRIMARY KEY (id);


--
-- Name: criterionanswers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY criterionanswers
    ADD CONSTRAINT criterionanswers_pkey PRIMARY KEY (id);


--
-- Name: delivery_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY delivery
    ADD CONSTRAINT delivery_pkey PRIMARY KEY (id);


--
-- Name: evaluation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY evaluation
    ADD CONSTRAINT evaluation_pkey PRIMARY KEY (id);


--
-- Name: evaluationrealized_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY evaluationrealized
    ADD CONSTRAINT evaluationrealized_pkey PRIMARY KEY (id);


--
-- Name: externallink_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY externallink
    ADD CONSTRAINT externallink_pkey PRIMARY KEY (id);


--
-- Name: forum_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY forum
    ADD CONSTRAINT forum_pkey PRIMARY KEY (id);


--
-- Name: game_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY game
    ADD CONSTRAINT game_pkey PRIMARY KEY (id);


--
-- Name: historylearningobject_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY historylearningobject
    ADD CONSTRAINT historylearningobject_pkey PRIMARY KEY (id);


--
-- Name: homework_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY homework
    ADD CONSTRAINT homework_pkey PRIMARY KEY (id);


--
-- Name: image_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY image
    ADD CONSTRAINT image_pkey PRIMARY KEY (id);


--
-- Name: keyword_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY keyword
    ADD CONSTRAINT keyword_name_key UNIQUE (name);


--
-- Name: keyword_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY keyword
    ADD CONSTRAINT keyword_pkey PRIMARY KEY (id);


--
-- Name: keywordsofcourse_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY keywordsofcourse
    ADD CONSTRAINT keywordsofcourse_pkey PRIMARY KEY (course_id, keywords_id);


--
-- Name: learningobject_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY learningobject
    ADD CONSTRAINT learningobject_pkey PRIMARY KEY (id);


--
<<<<<<< HEAD
-- Name: log_primary_key; Type: CONSTRAINT; Schema: public; Owner: amadeus; Tablespace: 
--

ALTER TABLE ONLY log
    ADD CONSTRAINT log_primary_key PRIMARY KEY (id);


--
=======
>>>>>>> 661708b07f533da1f47ab2b8c362cb287fdf4631
-- Name: material_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY material
    ADD CONSTRAINT material_pkey PRIMARY KEY (id);


--
-- Name: materialrequest_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY materialrequest
    ADD CONSTRAINT materialrequest_pkey PRIMARY KEY (id);


--
-- Name: message_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY message
    ADD CONSTRAINT message_pkey PRIMARY KEY (id);


--
-- Name: module_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY module
    ADD CONSTRAINT module_pkey PRIMARY KEY (id);


--
-- Name: openid_identity_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY openid
    ADD CONSTRAINT openid_identity_key UNIQUE (_identity);


--
-- Name: openid_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY openid
    ADD CONSTRAINT openid_pkey PRIMARY KEY (id);


--
-- Name: person_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_email_key UNIQUE (email);


--
-- Name: person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- Name: person_role_course_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY person_role_course
    ADD CONSTRAINT person_role_course_pkey PRIMARY KEY (id);


--
-- Name: poll_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY poll
    ADD CONSTRAINT poll_pkey PRIMARY KEY (id);


--
-- Name: primarykeyconstraint; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY evaluation_question
    ADD CONSTRAINT primarykeyconstraint PRIMARY KEY (evaluation_id, question_id);


--
-- Name: question_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY question
    ADD CONSTRAINT question_pkey PRIMARY KEY (id);


--
-- Name: questionrealized_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY questionrealized
    ADD CONSTRAINT questionrealized_pkey PRIMARY KEY (id);


--
-- Name: resume_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY resume
    ADD CONSTRAINT resume_pkey PRIMARY KEY (id);


--
-- Name: role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: uniquekey_person_course; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY person_role_course
    ADD CONSTRAINT uniquekey_person_course UNIQUE (person_id, course_id);


--
-- Name: uniquekey_person_evaluation; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY evaluationrealized
    ADD CONSTRAINT uniquekey_person_evaluation UNIQUE (person_id, evaluation_id);


--
-- Name: userrequest_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY userrequest
    ADD CONSTRAINT userrequest_pkey PRIMARY KEY (id);


--
-- Name: videoiriz_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY videoiriz
    ADD CONSTRAINT videoiriz_pkey PRIMARY KEY (id);


--
-- Name: fk1535e21440c3070c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gapanswer
    ADD CONSTRAINT fk1535e21440c3070c FOREIGN KEY (questionrealized_id) REFERENCES questionrealized(id);


--
-- Name: fk15adc9475d0e3509; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY material
    ADD CONSTRAINT fk15adc9475d0e3509 FOREIGN KEY (request_id) REFERENCES materialrequest(id);


--
-- Name: fk15adc947a6287150; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY material
    ADD CONSTRAINT fk15adc947a6287150 FOREIGN KEY (archive_id) REFERENCES archive(id);


--
-- Name: fk15adc947a7537834; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY material
    ADD CONSTRAINT fk15adc947a7537834 FOREIGN KEY (author_id) REFERENCES person(id);


--
-- Name: fk15adc947ff6dc8a4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY material
    ADD CONSTRAINT fk15adc947ff6dc8a4 FOREIGN KEY (module_id) REFERENCES module(id);


--
-- Name: fk1c3e4b288ea5370; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY criterion_criterionanswers
    ADD CONSTRAINT fk1c3e4b288ea5370 FOREIGN KEY (criterion_id) REFERENCES criterion(id);


--
-- Name: fk1c3e4b2c49525a3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY criterion_criterionanswers
    ADD CONSTRAINT fk1c3e4b2c49525a3 FOREIGN KEY (answers_id) REFERENCES criterionanswers(id);


--
-- Name: fk21c012ff6dc8a4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY game
    ADD CONSTRAINT fk21c012ff6dc8a4 FOREIGN KEY (module_id) REFERENCES module(id);


--
-- Name: fk24a217aa39b57ec2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY evaluationrealized
    ADD CONSTRAINT fk24a217aa39b57ec2 FOREIGN KEY (evaluation_id) REFERENCES evaluation(id);


--
-- Name: fk24a217aa823a322a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY evaluationrealized
    ADD CONSTRAINT fk24a217aa823a322a FOREIGN KEY (person_id) REFERENCES person(id);


--
-- Name: fk260bdfff6dc8a4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY poll
    ADD CONSTRAINT fk260bdfff6dc8a4 FOREIGN KEY (module_id) REFERENCES module(id);


--
-- Name: fk33e78f316c6b0f66; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY courseevaluation_commentary
    ADD CONSTRAINT fk33e78f316c6b0f66 FOREIGN KEY (commentaries_id) REFERENCES commentary(id);


--
-- Name: fk33e78f31ed65d464; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY courseevaluation_commentary
    ADD CONSTRAINT fk33e78f31ed65d464 FOREIGN KEY (courseevaluation_id) REFERENCES courseevaluation(id);


--
-- Name: fk34ef8014823a322a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY delivery
    ADD CONSTRAINT fk34ef8014823a322a FOREIGN KEY (person_id) REFERENCES person(id);


--
-- Name: fk34ef8014a16e7a4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY delivery
    ADD CONSTRAINT fk34ef8014a16e7a4 FOREIGN KEY (homework_id) REFERENCES homework(id);


--
-- Name: fk40e9d01ff6dc8a4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY forum
    ADD CONSTRAINT fk40e9d01ff6dc8a4 FOREIGN KEY (module_id) REFERENCES module(id);


--
-- Name: fk53fbe3afc72997b7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY associationrealized
    ADD CONSTRAINT fk53fbe3afc72997b7 FOREIGN KEY (questionrealized_id) REFERENCES questionrealized(id);


--
-- Name: fk56da32dddbc23d07; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY courseevaluation_person
    ADD CONSTRAINT fk56da32dddbc23d07 FOREIGN KEY (students_id) REFERENCES person(id);


--
-- Name: fk56da32dded65d464; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY courseevaluation_person
    ADD CONSTRAINT fk56da32dded65d464 FOREIGN KEY (courseevaluation_id) REFERENCES courseevaluation(id);


--
-- Name: fk57aecf8d24818534; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY alternative
    ADD CONSTRAINT fk57aecf8d24818534 FOREIGN KEY (question_id) REFERENCES question(id);


--
-- Name: fk5af7f43c11d61984; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY coursescores
    ADD CONSTRAINT fk5af7f43c11d61984 FOREIGN KEY (student_id) REFERENCES person(id);


--
-- Name: fk5af7f43cff6dc8a4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY coursescores
    ADD CONSTRAINT fk5af7f43cff6dc8a4 FOREIGN KEY (module_id) REFERENCES module(id);


--
-- Name: fk5e3284616862da32; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY association
    ADD CONSTRAINT fk5e3284616862da32 FOREIGN KEY (questionassociation_id) REFERENCES question(id);


--
-- Name: fk752f2bde1f427644; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY answer
    ADD CONSTRAINT fk752f2bde1f427644 FOREIGN KEY (poll_id) REFERENCES poll(id);


--
-- Name: fk752f2bde823a322a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY answer
    ADD CONSTRAINT fk752f2bde823a322a FOREIGN KEY (person_id) REFERENCES person(id);


--
-- Name: fk784249c11f427644; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY choice
    ADD CONSTRAINT fk784249c11f427644 FOREIGN KEY (poll_id) REFERENCES poll(id);


--
-- Name: fk78a7cc3b83dde8d0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY course
    ADD CONSTRAINT fk78a7cc3b83dde8d0 FOREIGN KEY (professor_id) REFERENCES person(id);


--
-- Name: fk867521917f54daa1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY historylearningobject
    ADD CONSTRAINT fk867521917f54daa1 FOREIGN KEY (learning_object_id) REFERENCES learningobject(id);


--
-- Name: fk86752191823a322a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY historylearningobject
    ADD CONSTRAINT fk86752191823a322a FOREIGN KEY (person_id) REFERENCES person(id);


--
-- Name: fk89b0928ca246b644; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY module
    ADD CONSTRAINT fk89b0928ca246b644 FOREIGN KEY (course_id) REFERENCES course(id);


--
-- Name: fk8d28b1057f91492a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY openid
    ADD CONSTRAINT fk8d28b1057f91492a FOREIGN KEY (accessinfo_id) REFERENCES accessinfo(id);


--
-- Name: fk8e4887752188614a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY person
    ADD CONSTRAINT fk8e4887752188614a FOREIGN KEY (image_id) REFERENCES image(id);


--
-- Name: fk8e48877559d6cf33; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY person
    ADD CONSTRAINT fk8e48877559d6cf33 FOREIGN KEY (access_info_id) REFERENCES accessinfo(id);


--
-- Name: fk8e488775e65ce32a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY person
    ADD CONSTRAINT fk8e488775e65ce32a FOREIGN KEY (resume_id) REFERENCES resume(id);


--
-- Name: fk92ba7c24823a322a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY userrequest
    ADD CONSTRAINT fk92ba7c24823a322a FOREIGN KEY (person_id) REFERENCES person(id);


--
-- Name: fk92ba7c24a246b644; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY userrequest
    ADD CONSTRAINT fk92ba7c24a246b644 FOREIGN KEY (course_id) REFERENCES course(id);


--
-- Name: fk99c042db49a6ea84; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY alternativerealized
    ADD CONSTRAINT fk99c042db49a6ea84 FOREIGN KEY (questionrealized_id) REFERENCES questionrealized(id);


--
-- Name: fk99c042db97456112; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY alternativerealized
    ADD CONSTRAINT fk99c042db97456112 FOREIGN KEY (alternative_id) REFERENCES alternative(id);


--
-- Name: fk9c2397e7a7537834; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY message
    ADD CONSTRAINT fk9c2397e7a7537834 FOREIGN KEY (author_id) REFERENCES person(id);


--
-- Name: fk9c2397e7c475de70; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY message
    ADD CONSTRAINT fk9c2397e7c475de70 FOREIGN KEY (forum_id) REFERENCES forum(id);


--
-- Name: fk9f19801dff6dc8a4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY learningobject
    ADD CONSTRAINT fk9f19801dff6dc8a4 FOREIGN KEY (module_id) REFERENCES module(id);


--
-- Name: fka271969c5b17a68f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY keywordsofcourse
    ADD CONSTRAINT fka271969c5b17a68f FOREIGN KEY (keywords_id) REFERENCES keyword(id);


--
-- Name: fka271969ca246b644; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY keywordsofcourse
    ADD CONSTRAINT fka271969ca246b644 FOREIGN KEY (course_id) REFERENCES course(id);


--
-- Name: fka9fde35cff6dc8a4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY evaluation
    ADD CONSTRAINT fka9fde35cff6dc8a4 FOREIGN KEY (module_id) REFERENCES module(id);


--
-- Name: fkb1f68a5435ce4882; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY questionrealized
    ADD CONSTRAINT fkb1f68a5435ce4882 FOREIGN KEY (question_id) REFERENCES question(id);


--
-- Name: fkb1f68a5473cede12; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY questionrealized
    ADD CONSTRAINT fkb1f68a5473cede12 FOREIGN KEY (evaluationrealized_id) REFERENCES evaluationrealized(id);


--
-- Name: fkb71889c0a246b644; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY course_coursescores
    ADD CONSTRAINT fkb71889c0a246b644 FOREIGN KEY (course_id) REFERENCES course(id);


--
-- Name: fkb71889c0d1da0a67; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY course_coursescores
    ADD CONSTRAINT fkb71889c0d1da0a67 FOREIGN KEY (studentsscores_id) REFERENCES coursescores(id);


--
-- Name: fkb72bfe85ff6dc8a4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY externallink
    ADD CONSTRAINT fkb72bfe85ff6dc8a4 FOREIGN KEY (module_id) REFERENCES module(id);


--
-- Name: fkbe5ca006a246b644; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY question
    ADD CONSTRAINT fkbe5ca006a246b644 FOREIGN KEY (course_id) REFERENCES course(id);


--
-- Name: fkc6e06715ff6dc8a4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY videoiriz
    ADD CONSTRAINT fkc6e06715ff6dc8a4 FOREIGN KEY (module_id) REFERENCES module(id);


--
-- Name: fkdde91348ff6dc8a4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY materialrequest
    ADD CONSTRAINT fkdde91348ff6dc8a4 FOREIGN KEY (module_id) REFERENCES module(id);


--
-- Name: fke6ef9890ff6dc8a4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY homework
    ADD CONSTRAINT fke6ef9890ff6dc8a4 FOREIGN KEY (module_id) REFERENCES module(id);


--
-- Name: fkf8b89e1a823a322a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY person_role_course
    ADD CONSTRAINT fkf8b89e1a823a322a FOREIGN KEY (person_id) REFERENCES person(id);


--
-- Name: fkf8b89e1a890ca6e4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY person_role_course
    ADD CONSTRAINT fkf8b89e1a890ca6e4 FOREIGN KEY (role_id) REFERENCES role(id);


--
-- Name: fkf8b89e1aa246b644; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY person_role_course
    ADD CONSTRAINT fkf8b89e1aa246b644 FOREIGN KEY (course_id) REFERENCES course(id);


--
-- Name: fkfe0dea1982f6e1f2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY courseevaluation_criterion
    ADD CONSTRAINT fkfe0dea1982f6e1f2 FOREIGN KEY (criteria_id) REFERENCES criterion(id);


--
-- Name: fkfe0dea19ed65d464; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY courseevaluation_criterion
    ADD CONSTRAINT fkfe0dea19ed65d464 FOREIGN KEY (courseevaluation_id) REFERENCES courseevaluation(id);


--
<<<<<<< HEAD
-- Name: log_person_foreign_key; Type: FK CONSTRAINT; Schema: public; Owner: amadeus
--

ALTER TABLE ONLY log
    ADD CONSTRAINT log_person_foreign_key FOREIGN KEY (person_id) REFERENCES person(id);


--
=======
>>>>>>> 661708b07f533da1f47ab2b8c362cb287fdf4631
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

<<<<<<< HEAD
=======
ALTER TABLE game ADD COLUMN linkexterno boolean DEFAULT false;

CREATE TABLE log
(
  id integer NOT NULL,
  date timestamp without time zone,
  person_id integer,
  codigo integer,
  idobjeto integer,
  fases integer,
  tempo integer,
  pontuacao integer,
  metaalternativa integer,
  tamanhomensagem integer,
  CONSTRAINT log_primary_key PRIMARY KEY (id ),
  CONSTRAINT log_person_foreign_key FOREIGN KEY (person_id)
      REFERENCES person (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
>>>>>>> 661708b07f533da1f47ab2b8c362cb287fdf4631

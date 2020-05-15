CREATE TABLE Audience_Evaluation_Trend (
    movie_id NUMBER, CONSTRAINT aet_movieid_pk PRIMARY KEY(movie_id),
    evaluation_point NUMBER ,
    people_count NUMBER ,
    male_rating NUMBER ,
    female_rating NUMBER ,
    age_10 NUMBER ,
    age_20 NUMBER ,
    age_30 NUMBER ,
    age_40 NUMBER ,
    age_50 NUMBER ,
    production_point NUMBER ,
    acting_point NUMBER ,
    story_point NUMBER ,
    visual_point NUMBER ,
    ost_point NUMBER 
);

CREATE TABLE Netizen_Evaluation_Trend (
    movie_id NUMBER, CONSTRAINT net_movieid_pk PRIMARY KEY(movie_id),
    evaluation_point NUMBER ,
    people_count NUMBER ,
    male_rating NUMBER ,
    female_rating NUMBER ,
    age_10 NUMBER ,
    age_20 NUMBER ,
    age_30 NUMBER ,
    age_40 NUMBER ,
    age_50 NUMBER ,
    production_point NUMBER ,
    acting_point NUMBER ,
    story_point NUMBER ,
    visual_point NUMBER ,
    ost_point NUMBER 
);

CREATE TABLE Watching_Trend (
    movie_id NUMBER, CONSTRAINT wt_movieid_pk PRIMARY KEY(movie_id),
    male_rating NUMBER ,
    female_rating NUMBER ,
    age_10 NUMBER ,
    age_20 NUMBER ,
    age_30 NUMBER ,
    age_40 NUMBER ,
    age_50 NUMBER
);
commit;
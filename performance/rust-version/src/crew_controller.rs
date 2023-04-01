use actix_web::{
    get,
    http::header::ContentType,
    post,
    web::{self, Path},
    HttpResponse, Responder,
};

use crate::crew::{get_all_crews, get_crew, get_crew_coll, Crew};

#[post("api/v1/crews/")]
async fn handle_create_crew(mut crew: web::Json<Crew>) -> impl Responder {
    let mut c = crew.into_inner();
    let result = get_crew_coll().await.insert_one(&c, None).await.unwrap();
    c.id = result.inserted_id.as_object_id();
    //TODO: should return 201 and not 200
    c
}

#[get("api/v1/crews/")]
async fn handle_get_all_crews() -> impl Responder {
    let response = serde_json::to_string(&(*(get_all_crews().await))).unwrap();

    HttpResponse::Ok()
        .content_type(ContentType::json())
        .body(response)
}

#[get("api/v1/crews/{id}")]
async fn handle_get_crew(path: Path<String>) -> impl Responder {
    let id = path.into_inner();
    get_crew(id).await
}

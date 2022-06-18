use actix_web::{
    get,
    http::header::ContentType,
    post,
    web::{self, Path},
    HttpResponse, Responder,
};

use crate::treasure::{get_all_treasures, get_treasure, get_treasure_coll, Treasure};

#[post("api/v1/treasures/")]
async fn handle_create_treasure(mut treasure: web::Json<Treasure>) -> impl Responder {
    let mut t = treasure.into_inner();
    let result = get_treasure_coll()
        .await
        .insert_one(&t, None)
        .await
        .unwrap();
    t.id = result.inserted_id.as_object_id();
    //TODO: should return 201 and not 200
    t
}

#[get("api/v1/treasures/")]
async fn handle_get_all_treasures() -> impl Responder {
    let response = serde_json::to_string(&(*(get_all_treasures().await))).unwrap();

    HttpResponse::Ok()
        .content_type(ContentType::json())
        .body(response)
}

#[get("api/v1/treasures/{id}")]
async fn handle_get_treasure(path: Path<String>) -> impl Responder {
    let id = path.into_inner();
    get_treasure(id).await
}

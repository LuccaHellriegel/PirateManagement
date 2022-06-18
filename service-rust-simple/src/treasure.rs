use crate::db::{get, get_all, get_db};
use actix_web::body::BoxBody;
use actix_web::http::header::ContentType;
use actix_web::{HttpRequest, HttpResponse, Responder};
use bigdecimal::BigDecimal;
use bson::serde_helpers::hex_string_as_object_id;
use mongodb::bson::oid::ObjectId;
use mongodb::{bson, Collection};
use serde::{Deserialize, Serialize};
use std::collections::HashSet;

#[derive(Debug, Serialize, Deserialize)]
pub struct Position {
    pub x: f32,
    pub y: f32,
}

#[derive(Debug, Serialize, Deserialize)]
pub struct Treasure {
    #[serde(
        default,
        rename = "_id",
        // with = "hex_string_as_object_id",
        skip_serializing_if = "Option::is_none"
    )]
    pub id: Option<ObjectId>,
    pub name: String,
    pub owner: String,
    pub position: Position,
    pub size: BigDecimal,
    pub assignedCrews: Vec<String>,
}

impl Responder for Treasure {
    type Body = BoxBody;

    fn respond_to(self, _req: &HttpRequest) -> HttpResponse<Self::Body> {
        let body = serde_json::to_string(&self).unwrap();

        HttpResponse::Ok()
            .content_type(ContentType::json())
            .body(body)
    }
}

static TREASURE_COLL: &str = "treasures";

pub async fn get_treasure_coll() -> Collection<Treasure> {
    get_db().await.collection(&TREASURE_COLL)
}

pub async fn get_treasure(id: String) -> Treasure {
    get::<Treasure>(&TREASURE_COLL, id).await
}

pub async fn get_all_treasures() -> Vec<Treasure> {
    get_all::<Treasure>(&TREASURE_COLL).await
}

//TODO
// func (t *Treasure) assignCrew(crew Crew) {
// 	t.AssignedCrews = append(t.AssignedCrews, crew.ID.Hex())
// }

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

//TODO: rename all to camelCase
//TODO: HashSet serialisation

#[derive(Debug, Serialize, Deserialize)]
pub struct Crew {
    #[serde(
        default,
        rename = "_id",
        //TODO: deserialise as string... -> but only for output????
        // with = "hex_string_as_object_id",
        skip_serializing_if = "Option::is_none",
        
    )]
    pub id: Option<ObjectId>,
    pub name: String,
    pub capacity: BigDecimal,
    pub usedCapacity: BigDecimal,
    pub assignedTreasures: Vec<String>,
}

impl Responder for Crew {
    type Body = BoxBody;

    fn respond_to(self, _req: &HttpRequest) -> HttpResponse<Self::Body> {
        let body = serde_json::to_string(&self).unwrap();

        HttpResponse::Ok()
            .content_type(ContentType::json())
            .body(body)
    }
}

static CREW_COLL: &str = "crews";

pub async fn get_crew_coll() -> Collection<Crew> {
    get_db().await.collection(&CREW_COLL)
}

pub async fn get_crew(id: String) -> Crew {
    get::<Crew>(&CREW_COLL, id).await
}

pub async fn get_all_crews() -> Vec<Crew> {
    get_all::<Crew>(&CREW_COLL).await
}

// func (c *Crew) calculateNewUsedCapacity(toBeAdded Treasure) decimal.Decimal {
// 	var assignedTreasuresSize = c.UsedCapacity.Add(toBeAdded.Size)
// 	if assignedTreasuresSize.Cmp(c.Capacity) > 0 {
// 		panic("Crew " + c.ID.Hex() + "'s capacity " + c.Capacity.String() + " was exceeded by the assigned treasure size " + assignedTreasuresSize.String() + ".")
// 		//TODO: learn proper error handling? return errors?
// 	}

// 	return assignedTreasuresSize
// }

// func (c *Crew) assignTreasure(treasure Treasure) {
// 	if !slices.Contains(c.AssignedTreasures, treasure.ID.Hex()) {
// 		c.UsedCapacity = c.calculateNewUsedCapacity(treasure)
// 		treasure.AssignedCrews = append(treasure.AssignedCrews, treasure.ID.Hex())
// 	}
// }

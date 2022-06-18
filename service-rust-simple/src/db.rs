use futures::stream::StreamExt;
use mongodb::{bson::doc, Client, Database};
use serde::de::DeserializeOwned;

pub async fn get_db() -> Database {
    let uri = "mongodb://localhost:27017/";
    let client = Client::with_uri_str(uri).await.unwrap();
    client.database("test")
}

pub async fn get<M: Send + Unpin + DeserializeOwned + Sync>(coll_name: &str, id: String) -> M {
    get_db()
        .await
        .collection(&coll_name)
        .find_one(doc! { "_id": id}, None)
        .await
        .unwrap()
        .unwrap()
}

pub async fn get_all<M: Send + Unpin + DeserializeOwned + Sync>(coll_name: &str) -> Vec<M> {
    let mut cursor = get_db()
        .await
        .collection(&coll_name)
        .find(None, None)
        .await
        .unwrap();

    let mut ms: Vec<M> = vec![];

    while let Some(m) = cursor.next().await {
        //TODO: proper error handling in this whole thing! Should be not so hard to just map to an error code?
        //impl ResponseError for
        ms.push(m.unwrap())
    }
    return ms;
}

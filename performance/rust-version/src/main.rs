use actix_web::{App, HttpServer};
use crew_controller::{handle_create_crew, handle_get_all_crews, handle_get_crew};
use treasure_controller::{handle_create_treasure, handle_get_all_treasures, handle_get_treasure};

mod crew;
mod crew_controller;
mod db;
mod treasure;
mod treasure_controller;

#[actix_web::main]
async fn main() -> std::io::Result<()> {
    //couldnt figure out how to do dependency inversion here and hand the app to the setup function

    HttpServer::new(|| {
        App::new()
            // Crew
            .service(handle_create_crew)
            .service(handle_get_crew)
            .service(handle_get_all_crews)
            // Treasure
            .service(handle_create_treasure)
            .service(handle_get_treasure)
            .service(handle_get_all_treasures)
    })
    .bind(("127.0.0.1", 8080))?
    .run()
    .await
}

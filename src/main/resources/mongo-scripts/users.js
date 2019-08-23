use web_customer_tracker
db.users.insertMany([
{'username':'admin','password':'{bcrypt}$2a$10$g8TrdyH2CtQheK1P65uwTOQonHPHzJePM0CIYQTZDmcaS2MOlX81G','enabled': true},
{'username':'mary','password':'{bcrypt}$2a$10$g8TrdyH2CtQheK1P65uwTOQonHPHzJePM0CIYQTZDmcaS2MOlX81G','enabled': true},
{'username':'susan','password':'{bcrypt}$2a$10$g8TrdyH2CtQheK1P65uwTOQonHPHzJePM0CIYQTZDmcaS2MOlX81G','enabled': true}
])
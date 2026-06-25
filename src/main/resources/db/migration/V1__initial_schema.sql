CREATE TABLE ingredients (

                             id BIGSERIAL PRIMARY KEY,

                             name VARCHAR(255) NOT NULL UNIQUE,

                             type VARCHAR(255) NOT NULL,

                             quantity DOUBLE PRECISION,

                             unit VARCHAR(50) NOT NULL
);


CREATE TABLE recipes (

                         id BIGSERIAL PRIMARY KEY,

                         name VARCHAR(255) NOT NULL,

                         style VARCHAR(255) NOT NULL,

                         instructions VARCHAR(1000) NOT NULL
);


CREATE TABLE recipe_ingredients (

                                    recipe_id BIGINT NOT NULL,

                                    ingredient_id BIGINT NOT NULL,

                                    PRIMARY KEY(recipe_id, ingredient_id),

                                    CONSTRAINT fk_recipe
                                        FOREIGN KEY(recipe_id)
                                            REFERENCES recipes(id)
                                            ON DELETE CASCADE,

                                    CONSTRAINT fk_ingredient
                                        FOREIGN KEY(ingredient_id)
                                            REFERENCES ingredients(id)
                                            ON DELETE CASCADE
);
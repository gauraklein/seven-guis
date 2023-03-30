# seven-guis

I chose to use `shadow-cljs` with `reagent` and `reframe` as the stack for this challenge.

This challenge was fun to do and I feel that I've implemented the requirements to the best of my ability. There are some areas in which I would like to improve if I had more time. Those are as follows:
- Break up the `events ns` a bit. Currently it's a wall of events, I think gui specific files would make the most sense.
- Add better error handling for inputs. I halfway implemented an error state for the temperature converter but it would be nice to add some clear messaging and symbols to the different guis when we reach error states.
- Add some unit tests

### Running the App

```sh
npm install
npx shadow-cljs watch app
```

Please be patient; it may take over 20 seconds to see any output, and over 40 seconds to complete.

When `[:app] Build completed` appears in the output, browse to
[http://localhost:8280/](http://localhost:8280/).
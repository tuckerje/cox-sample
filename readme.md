The sample rest client is a Spring boot application taking advantage of the
RestTemplate API. The main code to look at is the SampleRestClient class.
The getDataSet() method views the given problem as a partial order set where
parallel streams are set up for the requests which can be run at the same time.
The current response is generating under 16 seconds as the four requests are needed to run in order:
1. generate id
2. get vehicle keys
3. get vehicle details
4. get dealer details if unique
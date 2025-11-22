# Parking-System
Project Brief:
Design a low-level architecture for backed system of a smart parking lot. This system should handel vehicle entery and exit management, parking space allocation, and fee calculation

Problem Statement:Imagine a parking lot in an urban area with multiple floors and numerous parking spots. 
- Efficient parking management process.
- The system should assign parking spot ased on vehicle size and avaliability, track the time each vehicle spends.
- Track the time each vehicle spends in parking lot, and calculate parking fees upon exit.

WHAT ARE WE SOLVING?
we will build a backend system for smart parking lot that can:
- Track vehicle entering and leaving
- Assign approproiate parking spots automatically
- Track how long each vehicle stays
-  calculate fees accurately
-  keep real-time avaliability updated
-  Handles multiple vehicle enetring/exiting at same time
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
FUNDAMENTAL REQUIREMENTS:
1)Parking Spot Allocation
- System must automatically assign a free spot based on vehicle size:
     - Motorcycle->small spot
     - Car->medium spot
     - Bus-> large spot
  - Only parking space that fit the vehicle type can be assigned
  - If no spot is available"Parking Full"
2)Vehicle Check-In
- Record
    - Vehicle number
    - Vehicle type(car/bike/bus)
    - Entry timestamp
    - Allocation parking spot
- Mark the spot occupied
3)Vehicle Check-Out
- System retrives entery time
-  calculates duration
-  calculates fee
-  Mark spot avaliable again
4)Parking Fee Caluculation
- Duration
- Vehicle type rate
- optional:
    - First 30 minute free?
    - Max daily cap?
    - Per hour or per minute billing?
5)Real Time Avaliability
System must update:
- Total Free spots
- Free spots by type
- Current occupancy
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
NON FUNCTIONAL REQUIREMENTS(IMP FOR LLD)
A)Performance
- Fast spot allocation
- fast Fee
B) Concurrency
Multiple cars may enter at once:
- Need thread safe shared structires
- Avoid 2 cars getting the same spot
C) Reliability
System must never
- Lose transaction data
- Miscalculate time
- Assign same spot twice
D) Scalability
Should work in a parking lot with
  -20 spots
  - 1000+ spots across pultiple floors

## credit-limit

#### Create Limit Offer
Allows to create a limit offer for an account. Offer should only be created for a greater limit than current limit.

###### Parameters
accountId
newLimit
limitType
offerActivationTime
offerExpiryTime




#### List Active Limit Offers
Allows to fetch active offers for a given account id and active date.
###### Parameters

accountId
activeDate




#### Update Limit Offer Status
Allows to update status of an active and pending offer to accepted and rejected.
###### Parameters

limitOfferId
status


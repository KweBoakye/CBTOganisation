package com.fyp.kweku.cbtoganisation.common

import java.util.*

interface UuidSource {

    // provides UUID
    companion object {fun random(): UUID{return UUID.randomUUID()}}

}
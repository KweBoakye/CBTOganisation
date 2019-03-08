package com.fyp.kweku.cbtoganisation.common

import java.util.*

interface UuidSource {
    companion object {fun random(): UUID{return UUID.randomUUID()}}

}
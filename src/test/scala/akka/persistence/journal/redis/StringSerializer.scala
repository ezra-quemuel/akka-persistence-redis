/*
 * Copyright © 2017 Safety Data - CFH SAS.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package akka
package persistence
package journal
package redis

import actor.ExtendedActorSystem

import spray.json._

class StringSerializer(system: ExtendedActorSystem) extends EventAdapter {
  override def manifest(event: Any): String =
    ""

  override def toJournal(event: Any): Any = event match {
    case s: String => JsString(s)
    case _         => event
  }

  override def fromJournal(event: Any, manifest: String): EventSeq = event match {
    case JsString(s) => EventSeq.single(s)
    case _           => EventSeq.single(event)
  }
}

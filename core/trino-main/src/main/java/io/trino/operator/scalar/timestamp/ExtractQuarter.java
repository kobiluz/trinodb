/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.trino.operator.scalar.timestamp;

import io.trino.spi.function.Description;
import io.trino.spi.function.LiteralParameters;
import io.trino.spi.function.ScalarFunction;
import io.trino.spi.function.SqlType;
import io.trino.spi.type.LongTimestamp;
import io.trino.spi.type.StandardTypes;
import org.joda.time.chrono.ISOChronology;

import static io.trino.operator.scalar.QuarterOfYearDateTimeField.QUARTER_OF_YEAR;
import static io.trino.type.DateTimes.scaleEpochMicrosToMillis;

@Description("Quarter of the year of the given timestamp")
@ScalarFunction("quarter")
public final class ExtractQuarter
{
    private ExtractQuarter() {}

    @LiteralParameters("p")
    @SqlType(StandardTypes.BIGINT)
    public static long extract(@SqlType("timestamp(p)") long timestamp)
    {
        return QUARTER_OF_YEAR.getField(ISOChronology.getInstanceUTC()).get(scaleEpochMicrosToMillis(timestamp));
    }

    @LiteralParameters("p")
    @SqlType(StandardTypes.BIGINT)
    public static long extract(@SqlType("timestamp(p)") LongTimestamp timestamp)
    {
        return extract(timestamp.getEpochMicros());
    }
}

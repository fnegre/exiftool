/**
 * Copyright 2015 Mickael Jeanroy <mickael.jeanroy@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thebuzzmedia.exiftool;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TagHandlerTest {

	@Test
	public void it_should_read_null_line() {
		TagHandler handler = new TagHandler();
		boolean hasNext = handler.readLine(null);
		assertThat(hasNext).isFalse();
		assertThat(handler.getTags())
			.isNotNull()
			.isEmpty();
	}

	@Test
	public void it_should_read_last_line() {
		TagHandler handler = new TagHandler();
		boolean hasNext = handler.readLine("{ready}");
		assertThat(hasNext).isFalse();
		assertThat(handler.getTags())
			.isNotNull()
			.isEmpty();
	}

	@Test
	public void it_should_read_tag_line() {
		Tag tag = Tag.ARTIST;
		String value = "foobar";

		TagHandler handler = new TagHandler();
		boolean hasNext = handler.readLine(tag.getName() + ": " + value);

		assertThat(hasNext).isTrue();
		assertThat(handler.getTags())
			.isNotNull()
			.isNotEmpty()
			.hasSize(1)
			.containsEntry(tag, value);
	}
}